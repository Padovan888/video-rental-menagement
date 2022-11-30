package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.LocationModel;
import br.com.videolocadorapassatempo.service.dto.ViewLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ViewLocationMapper extends EntityMapper<ViewLocationDto, LocationModel> {

    @Override
    @Mapping(source = "customerModel.id", target = "idCustomer")
    @Mapping(source = "customerModel.name", target = "nameCustomer")
    ViewLocationDto toDto(LocationModel entity);

    @Override
    @Mapping(source = "idCustomer", target = "customerModel.id")
    @Mapping(source = "nameCustomer", target = "customerModel.name")
    LocationModel toEntity(ViewLocationDto dto);

}
