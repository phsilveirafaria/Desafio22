package br.com.desafio.controller.v1;

import br.com.desafio.controller.v1.api.AgendaApi;
import br.com.desafio.controller.v1.request.AgendaRequest;
import br.com.desafio.domain.Agenda;
import br.com.desafio.service.AgendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/agenda")
public class AgendaController implements AgendaApi {

    private final AgendaService agendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Agenda> createAgenda(
            @Valid @RequestBody final AgendaRequest agendaRequest) {
        return agendaService.createAgenda(agendaRequest)
                .doOnNext(a -> log.debug("Create new agenda - {}", a));
    }

    @GetMapping
    public Flux<Agenda> getAgendas() {
        return agendaService.findAll();
    }

}
