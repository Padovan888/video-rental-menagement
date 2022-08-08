package br.com.videolocadorapassatempo.service.exception;

public class ActorException extends RuntimeException{

    public ActorException(final String message) {
        this(message, null);
    }

    public ActorException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
