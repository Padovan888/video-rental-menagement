package br.com.videolocadorapassatempo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewTitleDto implements Serializable {

    private Long id;

    private LocalDate year;

    private String synopsis;

    private String category;

    private Long idDirector;

    private Long idClass;

    private List<TitleActorDto> titleActorDto = new ArrayList<>();

}
