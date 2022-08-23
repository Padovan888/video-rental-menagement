package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class TitleActorDto implements Serializable {

    private Long idActor;

    private String nameActor;

}
