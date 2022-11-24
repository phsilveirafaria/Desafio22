package br.com.desafio.exception;

public class UnableToVoteException extends RuntimeException {

    public UnableToVoteException(final String message) {
        super(String.format("document[%s].unableToVote", message));
    }

}
