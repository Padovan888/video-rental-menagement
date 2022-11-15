package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class LocationDto implements Serializable {

    private Long id;

    @NotNull(message = "O campo data de devolução não pode ser nulo!")
    private LocalDate leaseDate;

    @NotNull(message = "O campo data de devolução prevista não pode ser nulo!")
    @Future(message = "O campo data de devolução prevista deve possuir uma data que se encontra no futuro!")
    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    @NotNull(message = "O campo valor cobrado não pode ser nulo!")
    @Min(value = 0, message = "O campo valor cobrado deve assumir valores positivos e não nulos!")
    private Double value;

    private Double penalty;

    @NotNull(message = "O campo pago não pode ser nulo!")
    private Boolean paid;

    private Long idCustomer;

    private Long idDependent;

    @NotNull(message = "O campo item não pode ser nulo!")
    private Long idItem;

}
