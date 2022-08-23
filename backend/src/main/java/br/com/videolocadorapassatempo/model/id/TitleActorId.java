package br.com.videolocadorapassatempo.model.id;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TitleActorId implements Serializable {

    @Column(name = "id_title", nullable = false)
    private Long titleId;

    @Column(name = "id_actor", nullable = false)
    private Long actorId;

}
