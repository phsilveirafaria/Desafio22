package br.com.desafio.controller.v1.request;

import lombok.Data;

@Data
public class AgendaRequest {

    private String subject;
    private String description;

}
