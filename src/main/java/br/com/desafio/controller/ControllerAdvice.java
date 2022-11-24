package br.com.desafio.controller;

import br.com.desafio.controller.v1.response.ErrorResponse;
import br.com.desafio.exception.AgendaNotFoundException;
import br.com.desafio.exception.OpenSessionException;
import br.com.desafio.exception.SessionNotFoundException;
import br.com.desafio.exception.UnableToVoteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(OpenSessionException.class)
    public final ResponseEntity<ErrorResponse> handleOpenSessionException(OpenSessionException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Já existe uma sessão para esta pauta!")
                        .build());
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAgendaNotFoundException(AgendaNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Pauta não encontrada!")
                        .build());
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleSessionNotFoundException(SessionNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Sessão não encontrada!")
                        .build());
    }

    @ExceptionHandler(UnableToVoteException.class)
    public final ResponseEntity<ErrorResponse> handleUnableToVoteException(UnableToVoteException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .message("Associado incapaz de votar!")
                        .build());
    }

}
