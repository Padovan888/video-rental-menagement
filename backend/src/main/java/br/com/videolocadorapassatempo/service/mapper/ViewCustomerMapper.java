package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.CustomerModel;
import br.com.videolocadorapassatempo.service.dto.ViewCustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViewCustomerMapper extends EntityMapper<ViewCustomerDto, CustomerModel> {
    
}
