package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.service.dto.ActorTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorTitleMapper extends EntityMapper<ActorTitleDto, ActorModel>{

    @Override
    @Mapping(source = "id", target = "idActor")
    @Mapping(source = "name", target = "nameActor")
    ActorTitleDto toDto(ActorModel entity);

    @Override
    @Mapping(source = "idActor", target = "id")
    @Mapping(source = "nameActor", target = "name")
    ActorModel toEntity(ActorTitleDto dto);

}
