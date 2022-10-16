package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class DependentDto implements Serializable {

    private Long id;

    private Long registrationNumber;

    @NotEmpty(message = "O campo nome não pode ser vazio!")
    @NotNull(message = "O campo nome não pode ser nulo!")
    @Size(min = 3, message = "O campo nome deve possuir no mínimo 3 caracteres!")
    @Size(max = 50, message = "O campo nome deve possuir no máximo 50 caracteres!")
    private String name;

    @NotNull(message = "O campo data de aniversário não pode ser nulo!")
    @Past(message = "O campo data de aniversário deve possuir uma data que se encontra no passado!")
    private LocalDate birthDate;

    @NotNull(message = "O campo gênero não pode ser nulo!")
    private Character gender;

    private Boolean active;

    private Long idCustomer;

}
