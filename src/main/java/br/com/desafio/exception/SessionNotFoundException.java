package br.com.desafio.exception;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(final String message) {
        super(String.format("session[%s].notFound", message));
    }

}
