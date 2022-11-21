package br.com.videolocadorapassatempo.service.mapper;

import br.com.videolocadorapassatempo.model.MemberModel;
import br.com.videolocadorapassatempo.service.dto.CreateMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface CreateMemberMapper extends EntityMapper<CreateMemberDto, MemberModel> {


}
