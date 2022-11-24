package br.com.desafio.domain.mapper;

import br.com.desafio.controller.v1.request.SessionRequest;
import br.com.desafio.domain.Session;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SessionMapper {

    public static Session toSession(SessionRequest sessionRequest) {
        var now = LocalDateTime.now();
        return Session.builder()
                .agendaId(sessionRequest.getAgendaId())
                .sessionStart(now)
                .sessionEnd(now.plus(sessionRequest.getVotingTimeout(), ChronoUnit.MINUTES))
                .votes(new ArrayList<>())
                .build();
    }

}
