package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ActorDto implements Serializable {

    private UUID id;

    @NotEmpty(message = "O campo nome não pode ser vazio !")
    @NotNull(message = "O campo nome não pode ser nulo !")
    @Min(value = 3, message = "O campo nome deve possuir no mínimo 3 caracteres !")
    @Max(value = 50, message = "O campo nome deve possuir no máximo 50 caracteres !")
    private String name;

}
