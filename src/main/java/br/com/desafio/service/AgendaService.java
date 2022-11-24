package br.com.desafio.service;

import br.com.desafio.controller.v1.request.AgendaRequest;
import br.com.desafio.domain.Agenda;
import br.com.desafio.domain.mapper.AgendaMapper;
import br.com.desafio.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public Mono<Agenda> createAgenda(AgendaRequest agendaRequest) {
        return agendaRepository.save(AgendaMapper.toAgenda(agendaRequest));
    }

    public Flux<Agenda> findAll() {
        return agendaRepository.findAll();
    }

}
