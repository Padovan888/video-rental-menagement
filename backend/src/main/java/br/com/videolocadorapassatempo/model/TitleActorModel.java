package br.com.videolocadorapassatempo.model;

import br.com.videolocadorapassatempo.model.id.TitleActorId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "title_actor")
@Getter
@Setter
@NoArgsConstructor
public class TitleActorModel {

    @EmbeddedId
    private TitleActorId id;

    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "id_title", referencedColumnName = "id", nullable = false)
    private TitleModel titleModel;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "id_actor", referencedColumnName = "id", nullable = false)
    private ActorModel actorModel;

}
