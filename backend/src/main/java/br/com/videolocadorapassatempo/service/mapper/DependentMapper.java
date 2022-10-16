package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.DependentModel;
import br.com.videolocadorapassatempo.service.dto.DependentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DependentMapper extends EntityMapper<DependentDto, DependentModel> {

    @Override
    @Mapping(source = "customerModel.id", target = "idCustomer")
    DependentDto toDto(DependentModel entity);

    @Override
    @Mapping(source = "idCustomer", target = "customerModel.id")
    DependentModel toEntity(DependentDto dto);

}
