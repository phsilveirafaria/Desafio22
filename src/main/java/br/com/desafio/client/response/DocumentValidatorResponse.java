package br.com.desafio.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentValidatorResponse {

    private Status status;

    public enum Status {
        ABLE_TO_VOTE, UNABLE_TO_VOTE
    }

}
