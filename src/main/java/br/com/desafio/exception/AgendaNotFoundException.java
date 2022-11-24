package br.com.desafio.exception;

public class AgendaNotFoundException extends RuntimeException {

    public AgendaNotFoundException(final String message) {
        super(String.format("agenda[%s].notFound", message));
    }

}
