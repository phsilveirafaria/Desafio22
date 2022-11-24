package br.com.desafio.repository;

import br.com.desafio.domain.Session;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface SessionRepository extends ReactiveCrudRepository<Session, String> {

    Flux<Session> findByAgendaId(String agendaId);

    Flux<Session> findByConsolidatedFalseAndSessionEndLessThan(LocalDateTime dt);

}
