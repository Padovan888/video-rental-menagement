package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "title")
@Getter
@Setter
public class TitleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_title")
    @SequenceGenerator(name = "sequence_title", sequenceName = "sequence_title", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year", nullable = false)
    private LocalDate year;

    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_director", referencedColumnName = "id", nullable = false)
    private DirectorModel directorModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_class", referencedColumnName = "id", nullable = false)
    private ClassModel classModel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_actor",
            joinColumns = @JoinColumn(name = "id_title"),
            inverseJoinColumns = @JoinColumn(name = "id_actor"))
    private List<ActorModel> actorModel = new ArrayList<>();

}
