package br.com.desafio.controller.v1.request;

import br.com.desafio.domain.enums.VoteEnum;
import lombok.Data;

@Data
public class VoteRequest {

    private String document;
    private VoteEnum vote;
}
