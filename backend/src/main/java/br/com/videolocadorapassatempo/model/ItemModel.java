package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "item")
@Getter
@Setter
public class ItemModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_item")
    @SequenceGenerator(name = "sequence_item", sequenceName = "sequence_item", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "serial_number", nullable = false)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_title", referencedColumnName = "id", nullable = false)
    private TitleModel titleModel;

    @JoinColumn(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_type", nullable = false)
    private ItemTypeModel itemTypeModel;

}
