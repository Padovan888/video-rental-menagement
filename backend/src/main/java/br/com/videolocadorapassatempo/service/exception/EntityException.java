package br.com.videolocadorapassatempo.service.exception;

public class EntityException extends RuntimeException{

    public EntityException(final String message) {
        this(message, null);
    }

    public EntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
