package br.com.videolocadorapassatempo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public static String getMessageError(String entity, Long id) {
        return "Entidade " + entity + " de id = " + id + " não foi encontrada";
    }

}
