package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TitleDto implements Serializable {

    private Long id;

    @NotNull(message = "O campo ano não pode ser nulo!")
    @Min(value = 1850, message = "O campo ano deve possuir um valor maior que 1850!")
    @Max(value = 2022, message = "O campo ano não deve possuir um valor maior que o ano atual!")
    private Integer year;

    @NotEmpty(message = "O campo nome não pode ser vazio!")
    @NotNull(message = "O campo nome não pode ser nulo!")
    @Size(min = 3, message = "O campo nome deve possuir no mínimo 3 caracteres!")
    @Size(max = 50, message = "O campo nome deve possuir no máximo 50 caracteres!")
    private String name;

    @NotEmpty(message = "O campo sinopse não pode ser vazio!")
    @NotNull(message = "O campo sinopse não pode ser nulo!")
    @Size(min = 10, message = "O campo sinopse deve possuir no mínimo 10 caracteres!")
    @Size(max = 500, message = "O campo sinopse deve possuir no máximo 500 caracteres!")
    private String synopsis;

    @NotEmpty(message = "O campo categoria não pode ser vazio!")
    @NotNull(message = "O campo categoria não pode ser nulo!")
    @Size(min = 3, message = "O campo categoria deve possuir no mínimo 3 caracteres!")
    @Size(max = 50, message = "O campo categoria deve possuir no máximo 50 caracteres!")
    private String category;

    @NotNull(message = "O campo diretor não pode ser nulo!")
    private Long idDirector;

    @NotNull(message = "O campo classe não pode ser nulo!")
    private Long idClass;

    @NotNull(message = "A lista de atores não pode ser nula!")
    @Size(min = 1, message = "A lista de atores deve possuir no mínimo um ator!")
    private List<ActorTitleDto> actorIds = new ArrayList<>();

}
