package br.com.desafio.domain.mapper;

import br.com.desafio.controller.v1.request.AgendaRequest;
import br.com.desafio.domain.Agenda;

public class AgendaMapper {

    public static Agenda toAgenda(AgendaRequest agendaRequest) {
        return Agenda.builder()
                .subject(agendaRequest.getSubject())
                .description(agendaRequest.getDescription())
                .build();
    }

}
