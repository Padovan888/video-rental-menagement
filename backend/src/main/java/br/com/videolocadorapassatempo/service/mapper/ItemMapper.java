package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.ItemModel;
import br.com.videolocadorapassatempo.service.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, ItemModel>{

    @Override
    @Mapping(source = "titleModel.id", target = "idTitle")
    @Mapping(source = "itemTypeModel.id", target = "idItemType")
    ItemDto toDto(ItemModel entity);

    @Override
    @Mapping(source = "idTitle", target = "titleModel.id")
    @Mapping(source = "idItemType", target = "itemTypeModel.id")
    ItemModel toEntity(ItemDto dto);

}
