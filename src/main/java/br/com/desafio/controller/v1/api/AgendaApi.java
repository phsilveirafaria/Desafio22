package br.com.desafio.controller.v1.api;

import br.com.desafio.controller.v1.request.AgendaRequest;
import br.com.desafio.domain.Agenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "agenda", description = "API de pautas")
public interface AgendaApi {

    @Operation(
            summary = "Salvar pauta",
            description = "Salva a pauta na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta persistida com sucesso")
    })
    Mono<Agenda> createAgenda(AgendaRequest agendaRequest);

    @Operation(
            summary = "Listar pautas",
            description = "Retorna a lista de pautas persistidas na base",
            tags = { "agenda" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    Flux<Agenda> getAgendas();

}
