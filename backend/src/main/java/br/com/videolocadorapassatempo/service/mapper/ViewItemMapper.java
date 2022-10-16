package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.ItemModel;
import br.com.videolocadorapassatempo.service.dto.ViewItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViewItemMapper extends EntityMapper<ViewItemDto, ItemModel>{
}
