package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.ClassModel;
import br.com.videolocadorapassatempo.service.dto.ClassDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapper extends EntityMapper<ClassDto, ClassModel>{
}
