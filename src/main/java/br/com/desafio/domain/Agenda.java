package br.com.desafio.domain;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Document
public class Agenda {

    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

}
