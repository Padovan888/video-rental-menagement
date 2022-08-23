package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.TitleActorModel;
import br.com.videolocadorapassatempo.service.dto.TitleActorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TitleActorMapper extends EntityMapper<TitleActorDto, TitleActorModel> {

    @Override
    @Mapping(source = "actorModel.id", target = "idActor")
    @Mapping(source = "actorModel.name", target = "nameActor")
    TitleActorDto toDto(TitleActorModel entity);

    @Override
    @Mapping(source = "idActor", target = "actorModel.id")
    @Mapping(source = "nameActor", target = "actorModel.name")
    TitleActorModel toEntity(TitleActorDto dto);

}
