package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "location")
@Getter
@Setter
public class LocationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_location")
    @SequenceGenerator(name = "sequence_location", sequenceName = "sequence_location", allocationSize = 1)
    private Long id;

    @Column(name = "lease_date", nullable = false)
    private LocalDate leaseDate;

    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "penalty")
    private Double penalty;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private CustomerModel customerModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependent", referencedColumnName = "id")
    private DependentModel dependentModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    private ItemModel itemModel;

}
