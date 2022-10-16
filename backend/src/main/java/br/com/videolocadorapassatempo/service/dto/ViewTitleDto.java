package br.com.videolocadorapassatempo.service.dto;

import br.com.videolocadorapassatempo.model.ClassModel;
import br.com.videolocadorapassatempo.model.DirectorModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewTitleDto implements Serializable {

    private Long id;

    private Integer year;

    private String name;

    private String synopsis;

    private String category;

    private DirectorModel directorModel;

    private ClassModel classModel;

    private List<ActorTitleDto> actorsModel = new ArrayList<>();

}
