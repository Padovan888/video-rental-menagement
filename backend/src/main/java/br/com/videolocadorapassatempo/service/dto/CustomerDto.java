package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto implements Serializable {

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

    @NotEmpty(message = "O campo CPF não pode ser vazio!")
    @NotNull(message = "O campo CPF não pode ser nulo!")
    @CPF(message = "O campo CPF deve apresentar um CPF válido!")
    private String cpf;

    @NotEmpty(message = "O campo endereço não pode ser vazio!")
    @NotNull(message = "O campo endereço não pode ser nulo!")
    @Size(min = 10, message = "O campo endereço deve possuir no mínimo 10 caracteres!")
    @Size(max = 50, message = "O campo endereço deve possuir no máximo 50 caracteres!")
    private String address;

    @NotEmpty(message = "O campo telefone não pode ser vazio!")
    @NotNull(message = "O campo telefone não pode ser nulo!")
    @Size(min = 9, message = "O campo telefone deve possuir no mínimo 9 caracteres!")
    @Size(max = 14, message = "O campo telefone deve possuir no máximo 14 caracteres!")
    private String telephone;

}
