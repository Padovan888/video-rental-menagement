package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "class")
@Getter
@Setter
@NoArgsConstructor
public class ClassModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_class")
    @SequenceGenerator(name = "sequence_class", sequenceName = "sequence_class", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

}
