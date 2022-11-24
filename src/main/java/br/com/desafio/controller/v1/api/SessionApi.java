package br.com.desafio.controller.v1.api;

import br.com.desafio.controller.v1.request.SessionRequest;
import br.com.desafio.controller.v1.request.VoteRequest;
import br.com.desafio.controller.v1.response.ErrorResponse;
import br.com.desafio.domain.Session;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "session", description = "API de sessões")
public interface SessionApi {

    @Operation(
            summary = "Salvar sessão",
            description = "Salva a sessão na base",
            tags = { "session" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta persistida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Pauta não encontrada", content = @Content(schema = @Schema(oneOf = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Já existe uma sessão para esta pauta", content = @Content(schema = @Schema(oneOf = ErrorResponse.class)))
    })
    Mono<Session> createSession(SessionRequest sessionRequest);

    @Operation(
            summary = "Votar na sessão",
            description = "Salva o voto do associado na base",
            tags = { "session" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto persistido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Associado incapaz de votar", content = @Content(schema = @Schema(oneOf = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Sessão não encontrada", content = @Content(schema = @Schema(oneOf = ErrorResponse.class)))
    })
    Mono<Session> voteSession(String id, VoteRequest voteRequest);

    @Operation(
            summary = "Buscar sessão",
            description = "Retorna a sessão persistida na base pelo seu id",
            tags = { "session" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    Mono<Session> getSessionById(String id);

}
