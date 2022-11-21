package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.DependentModel;
import br.com.videolocadorapassatempo.service.dto.CreateDependentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG, uses = {ViewCustomerMapper.class})
public interface CreateDependentMapper extends EntityMapper<CreateDependentDto, DependentModel> {

    @Override
    @Mapping(source = "memberModel.id", target = "idMember")
    CreateDependentDto toDto(DependentModel entity);

    @Override
    @Mapping(source = "idMember", target = "memberModel.id")
    DependentModel toEntity(CreateDependentDto dto);

}
