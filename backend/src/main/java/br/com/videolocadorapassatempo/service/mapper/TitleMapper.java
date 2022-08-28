package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.TitleModel;
import br.com.videolocadorapassatempo.service.dto.TitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ActorTitleMapper.class})
public interface TitleMapper extends EntityMapper<TitleDto, TitleModel>{

    @Override
    @Mapping(source = "directorModel.id", target = "idDirector")
    @Mapping(source = "classModel.id", target = "idClass")
    @Mapping(source = "actorModel", target = "actorIds")
    TitleDto toDto(TitleModel entity);

    @Override
    @Mapping(source = "idDirector", target = "directorModel.id")
    @Mapping(source = "idClass", target = "classModel.id")
    @Mapping(source = "actorIds", target = "actorModel")
    TitleModel toEntity(TitleDto dto);

}
