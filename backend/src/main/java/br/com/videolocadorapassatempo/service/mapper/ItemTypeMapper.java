package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.ItemTypeModel;
import br.com.videolocadorapassatempo.service.dto.ItemTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemTypeMapper extends EntityMapper<ItemTypeDto, ItemTypeModel>{
}
