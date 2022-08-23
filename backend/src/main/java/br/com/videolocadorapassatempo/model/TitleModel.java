package br.com.videolocadorapassatempo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "title")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_title")
    @SequenceGenerator(name = "sequence_title", sequenceName = "sequence_title", allocationSize = 1)
    private Long id;

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

    @OneToMany(mappedBy = "titleModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TitleActorModel> titleActorModel = new ArrayList<>();

}
