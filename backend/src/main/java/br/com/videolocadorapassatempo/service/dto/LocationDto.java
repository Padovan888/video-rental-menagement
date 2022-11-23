package br.com.videolocadorapassatempo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Serializable {

    private Long id;

    private LocalDate leaseDate;

    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    private Double value;

    private Double penalty;

    private Boolean paid;

    private Long idCustomer;

    private Long idItem;

    private List<LocationDto> debitRecords = new ArrayList<>();

    public LocationDto(Long id,
                       LocalDate leaseDate,
                       LocalDate expectedReturnDate,
                       LocalDate actualReturnDate,
                       Double value,
                       Double penalty,
                       Boolean paid,
                       Long idCustomer,
                       Long idItem) {
        this.id = id;
        this.leaseDate = leaseDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.value = value;
        this.penalty = penalty;
        this.paid = paid;
        this.idCustomer = idCustomer;
        this.idItem = idItem;
    }
    
}
