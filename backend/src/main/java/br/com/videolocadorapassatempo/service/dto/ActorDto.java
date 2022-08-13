package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class ActorDto implements Serializable {

    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio !")
    @NotNull(message = "O campo nome não pode ser nulo !")
    @Size(min = 3, message = "O campo nome deve possuir no mínimo 3 caracteres !")
    @Size(max = 50, message = "O campo nome deve possuir no máximo 50 caracteres !")
    private String name;

}
