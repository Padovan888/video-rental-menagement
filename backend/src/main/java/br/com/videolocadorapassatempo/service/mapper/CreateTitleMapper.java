package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.TitleModel;
import br.com.videolocadorapassatempo.service.dto.CreateTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateTitleMapper extends EntityMapper<CreateTitleDto, TitleModel>{

    @Override
    @Mapping(source = "directorModel.id", target = "idDirector")
    @Mapping(source = "classModel.id", target = "idClass")
    CreateTitleDto toDto(TitleModel entity);

    @Override
    @Mapping(source = "idDirector", target = "directorModel.id")
    @Mapping(source = "idClass", target = "classModel.id")
    TitleModel toEntity(CreateTitleDto dto);

}
