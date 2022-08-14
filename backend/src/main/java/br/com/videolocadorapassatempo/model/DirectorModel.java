package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "director")
@Getter
@Setter
public class DirectorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencer_director")
    @SequenceGenerator(name = "sequence_director", sequenceName = "sequence_director", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
