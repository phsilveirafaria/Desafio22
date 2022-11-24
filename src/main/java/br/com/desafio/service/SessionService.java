package br.com.desafio.service;

import br.com.desafio.client.DocumentValidatorClient;
import br.com.desafio.client.response.DocumentValidatorResponse;
import br.com.desafio.controller.v1.request.SessionRequest;
import br.com.desafio.controller.v1.request.VoteRequest;
import br.com.desafio.domain.Session;
import br.com.desafio.domain.Vote;
import br.com.desafio.domain.mapper.SessionMapper;
import br.com.desafio.exception.AgendaNotFoundException;
import br.com.desafio.exception.OpenSessionException;
import br.com.desafio.exception.SessionNotFoundException;
import br.com.desafio.exception.UnableToVoteException;
import br.com.desafio.repository.AgendaRepository;
import br.com.desafio.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final AgendaRepository agendaRepository;
    private final SessionRepository sessionRepository;
    private final DocumentValidatorClient documentValidatorClient;
    private final QueueSender queueSender;

    public Mono<Session> createSession(SessionRequest sessionRequest) {
        return agendaRepository.findById(sessionRequest.getAgendaId())
                .switchIfEmpty(Mono.error(new AgendaNotFoundException(sessionRequest.getAgendaId())))
                .flatMap(agenda -> sessionRepository.findByAgendaId(sessionRequest.getAgendaId()).collectList())
                .flatMap(session -> Mono.justOrEmpty(session.stream().filter(s -> s.getSessionEnd().isAfter(now())).findFirst()))
                .flatMap(session -> Mono.error(new OpenSessionException(session.getAgendaId(), session.getId())))
                .switchIfEmpty(Mono.defer(() -> sessionRepository.save(SessionMapper.toSession(sessionRequest))))
                .cast(Session.class);
    }

    public Mono<Session> voteSession(String id, VoteRequest voteRequest) {
        return documentValidatorClient.validateDocument(voteRequest.getDocument())
                .flatMap(response -> {
                    if (DocumentValidatorResponse.Status.UNABLE_TO_VOTE.equals(response.getStatus())) {
                        return Mono.error(new UnableToVoteException(voteRequest.getDocument()));
                    }
                    return Mono.just(response);
                })
                .flatMap(__ -> sessionRepository.findById(id))
                .filter(session -> session.getSessionEnd().isAfter(now()))
                .switchIfEmpty(Mono.error(new SessionNotFoundException(id)))
                .map(session -> {
                    session.getVotes().stream()
                            .filter(vote -> vote.getDocument().equals(voteRequest.getDocument()))
                            .findFirst()
                            .ifPresentOrElse(vote -> vote.setVote(voteRequest.getVote()),
                                    () -> session.getVotes().add(Vote.builder()
                                            .vote(voteRequest.getVote())
                                            .document(voteRequest.getDocument()).build()));

                    return session;
                })
                .flatMap(sessionRepository::save);

    }

    public Mono<Session> findById(String id) {
        return sessionRepository.findById(id);
    }

    @Scheduled(fixedDelay = 5000)
    public void consolidateSession() {
        sessionRepository.findByConsolidatedFalseAndSessionEndLessThan(LocalDateTime.now())
                .flatMap(session -> {
                    queueSender.send(String.format("pastel[%s]", session.getId()));
                    session.setConsolidated(true);
                    return sessionRepository.save(session);
                })
                .doOnNext(session -> log.info("session {} successfully processed", session.getId()))
                .subscribeOn(Schedulers.immediate())
                .subscribe();
    }

}
