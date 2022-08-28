package br.com.videolocadorapassatempo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ActorTitleDto implements Serializable {

    @NotNull(message = "O campo identificador do ator não pode ser nulo!")
    @JsonProperty("id")
    private Long idActor;

    @JsonProperty("name")
    private String nameActor;

}
