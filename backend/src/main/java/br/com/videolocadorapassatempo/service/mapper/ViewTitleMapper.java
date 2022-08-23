package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.TitleModel;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TitleActorMapper.class})
public interface ViewTitleMapper extends EntityMapper<ViewTitleDto, TitleModel>{

    @Override
    @Mapping(source = "directorModel.id", target = "idDirector")
    @Mapping(source = "classModel.id", target = "idClass")
    @Mapping(source = "titleActorModel", target = "titleActorDto")
    ViewTitleDto toDto(TitleModel entity);

    @Override
    @Mapping(source = "idDirector", target = "directorModel.id")
    @Mapping(source = "idClass", target = "classModel.id")
    @Mapping(source = "titleActorDto", target = "titleActorModel")
    TitleModel toEntity(ViewTitleDto dto);

}
