package br.com.videolocadorapassatempo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityBadRequestException extends ResponseStatusException {

    public EntityBadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public static String getMessageErrorEntityLink(String entity, String link, Long id) {
        return "Entidade " + entity + " de id = " + id + " possui vínculo com a entidade " + link;
    }

}
