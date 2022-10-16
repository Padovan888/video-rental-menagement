package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dependent")
@Getter
@Setter
@NoArgsConstructor
public class DependentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_dependent")
    @SequenceGenerator(name = "sequence_dependent", sequenceName = "sequence_dependent", allocationSize = 1)
    private Long id;

    @Column(name = "registration_number", nullable = false)
    private Long registrationNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    private CustomerModel customerModel;

}
