package br.com.desafio.domain;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document
public class Session {

    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String agendaId;

    @NotBlank
    private LocalDateTime sessionStart;

    @NotBlank
    private LocalDateTime sessionEnd;

    private List<Vote> votes;

    private boolean consolidated;

}
