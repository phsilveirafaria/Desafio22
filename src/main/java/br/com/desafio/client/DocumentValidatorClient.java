package br.com.desafio.client;

import br.com.desafio.client.response.DocumentValidatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DocumentValidatorClient {

    private final WebClient client = WebClient.create();

    @Value("${client.cpf-validator.url}")
    private String url;

    public Mono<DocumentValidatorResponse> validateDocument(String document) {
        return client.get()
                .uri(url + document)
                .retrieve()
                .bodyToMono(DocumentValidatorResponse.class)
                .doOnNext(d -> log.debug("validateDocument {} - {}", document, d));
    }

}
