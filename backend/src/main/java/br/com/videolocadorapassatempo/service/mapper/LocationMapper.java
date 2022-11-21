//package br.com.videolocadorapassatempo.service.mapper;
//
//import br.com.videolocadorapassatempo.model.LocationModel;
//import br.com.videolocadorapassatempo.service.dto.LocationDto;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface LocationMapper extends EntityMapper<LocationDto, LocationModel> {
//
//    @Override
//    @Mapping(source = "customerModel.id", target = "idCustomer")
//    @Mapping(source = "dependentModel.id", target = "idDependent")
//    @Mapping(source = "itemModel.id", target = "idItem")
//    LocationDto toDto(LocationModel entity);
//
//    @Override
//    @Mapping(source = "idCustomer", target = "customerModel.id")
//    @Mapping(source = "idDependent", target = "dependentModel.id")
//    @Mapping(source = "idItem", target = "itemModel.id")
//    LocationModel toEntity(LocationDto dto);
//
//}
