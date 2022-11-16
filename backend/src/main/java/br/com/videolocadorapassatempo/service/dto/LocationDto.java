package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class LocationDto implements Serializable {

    private Long id;

    private LocalDate leaseDate;

    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    private Double value;

    private Double penalty;

    private Boolean paid;

    private Long idCustomer;

    private Long idDependent;

    private Long idItem;

}
