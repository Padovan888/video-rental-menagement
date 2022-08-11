package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class ActorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_actor")
    @SequenceGenerator(name = "sequence_actor", sequenceName = "sequence_actor", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
