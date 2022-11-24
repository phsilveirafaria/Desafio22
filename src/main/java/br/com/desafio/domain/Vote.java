package br.com.desafio.domain;

import br.com.desafio.domain.enums.VoteEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {

    private String document;
    private VoteEnum vote;

}
