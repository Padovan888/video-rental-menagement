package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ItemDto implements Serializable {

    private Long id;

    @NotEmpty(message = "O campo número de serial não pode ser vazio!")
    @NotNull(message = "O campo número de serial não pode ser nulo!")
    @Size(min = 10, message = "O campo número de serial deve possuir no mínimo 10 caracteres!")
    @Size(max = 50, message = "O campo número de serial deve possuir no máximo 50 caracteres!")
    private String serialNumber;

    @NotNull(message = "O campo título não pode ser nulo!")
    private Long idTitle;

    @NotNull(message = "O campo data de aquisição não pode ser nulo!")
    @PastOrPresent(message = "O campo data de aquisição deve possuir uma data no passado ou no presente!")
    private LocalDate purchaseDate;

    @NotNull(message = "O campo tipo de item não pode ser nulo!")
    private Long idItemType;

}
