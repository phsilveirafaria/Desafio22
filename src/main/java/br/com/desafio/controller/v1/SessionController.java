package br.com.desafio.controller.v1;

import br.com.desafio.controller.v1.api.SessionApi;
import br.com.desafio.controller.v1.request.SessionRequest;
import br.com.desafio.controller.v1.request.VoteRequest;
import br.com.desafio.domain.Session;
import br.com.desafio.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/session")
public class SessionController implements SessionApi {

    private final SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Session> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        return sessionService.createSession(sessionRequest)
                .doOnNext(s -> log.debug("Create new session - {}", s));
    }

    @PostMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Session> voteSession(@PathVariable final String id, @Valid @RequestBody VoteRequest voteRequest) {
        return sessionService.voteSession(id, voteRequest);
    }

    @GetMapping("/{id}")
    public Mono<Session> getSessionById(@PathVariable final String id) {
        return sessionService.findById(id);
    }

}
