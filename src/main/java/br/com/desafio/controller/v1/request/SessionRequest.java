package br.com.desafio.controller.v1.request;

import lombok.Data;

@Data
public class SessionRequest {

    private String agendaId;
    private Integer votingTimeout = 1;

}
