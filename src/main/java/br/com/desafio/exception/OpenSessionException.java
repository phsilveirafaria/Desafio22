package br.com.desafio.exception;

public class OpenSessionException extends RuntimeException {

    public OpenSessionException(final String agendaId, final String sessionId) {
        super(String.format("agenda[%s].session[%s].open", agendaId, sessionId));
    }

}
