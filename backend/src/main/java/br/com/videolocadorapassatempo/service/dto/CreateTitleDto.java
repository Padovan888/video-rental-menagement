package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateTitleDto {

    private Long id;

    @NotNull(message = "O campo ano não pode ser nulo!")
    @PastOrPresent(message = "O campo ano deve ser uma data que se encontra no passado ou no presente!")
    private LocalDate year;

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
    private List<Long> actorIds = new ArrayList<>();

}
