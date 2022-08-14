package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.DirectorModel;
import br.com.videolocadorapassatempo.service.dto.DirectorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper extends EntityMapper<DirectorDto, DirectorModel>{
}
