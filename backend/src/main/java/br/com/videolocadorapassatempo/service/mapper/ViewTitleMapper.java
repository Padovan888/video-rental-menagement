package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.TitleModel;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ActorTitleMapper.class})
public interface ViewTitleMapper extends EntityMapper<ViewTitleDto, TitleModel> {

    @Override
    @Mapping(source = "actorModel", target = "actorsModel")
    ViewTitleDto toDto(TitleModel entity);

    @Override
    @Mapping(source = "actorsModel", target = "actorModel")
    TitleModel toEntity(ViewTitleDto dto);

}
