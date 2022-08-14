package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DirectorDto {

    Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio!")
    @NotNull(message = "O campo nome não pode ser nulo!")
    @Size(min = 3, message = "O campo nome deve possuir no mínimo 3 caracteres!")
    @Size(max = 50, message = "O campo nome deve possuir no máximo 50 caracteres!")
    String name;

}
