package br.com.videolocadorapassatempo.service.dto;

import br.com.videolocadorapassatempo.model.ItemModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ViewLocationDto implements Serializable {

    private Long id;

    private LocalDate leaseDate;

    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    private Double value;

    private Double penalty;

    private Boolean paid;

    private Long idCustomer;

    private String nameCustomer;

    private ItemModel itemModel;

}
