package br.com.videolocadorapassatempo.service.dto;

import br.com.videolocadorapassatempo.model.ItemTypeModel;
import br.com.videolocadorapassatempo.model.TitleModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ViewItemDto implements Serializable {

    private Long id;

    private String serialNumber;

    private TitleModel titleModel;

    private LocalDate purchaseDate;

    private ItemTypeModel itemTypeModel;

}
