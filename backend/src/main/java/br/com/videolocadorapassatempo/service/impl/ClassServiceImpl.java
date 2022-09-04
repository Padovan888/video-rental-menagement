package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ClassModel;
import br.com.videolocadorapassatempo.repository.ClassRepository;
import br.com.videolocadorapassatempo.repository.TitleRepository;
import br.com.videolocadorapassatempo.service.ClassService;
import br.com.videolocadorapassatempo.service.dto.ClassDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    private final TitleRepository titleRepository;

    private final ClassMapper classMapper;

    @Transactional(readOnly = true)
    public List<ClassDto> findAll() {
        return classMapper.toDto(classRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ClassDto findById(Long idClass) {
        ClassModel classModel = classRepository.findById(idClass)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CLASS.getName(), idClass)));

        return classMapper.toDto(classModel);
    }

    public ClassDto create(ClassDto classDto) {
        return classMapper.toDto(classRepository.save(classMapper.toEntity(classDto)));
    }

    public ClassDto update(ClassDto classDto) {
        if(!classRepository.existsById(classDto.getId())) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CLASS.getName(), classDto.getId()));
        }

        return classMapper.toDto(classRepository.save(classMapper.toEntity(classDto)));
    }

    @Transactional(readOnly = true)
    private void restrictionsToDelete(Long idClass) {
        if(!classRepository.existsById(idClass)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CLASS.getName(), idClass));
        }

        if(titleRepository.existsByClassModelId(idClass)) {
            throw new EntityBadRequestException(EntityBadRequestException.getMessageErrorEntityLink(Entity.CLASS.getName(), Entity.TITLE.getName(), idClass));
        }
    }

    public void deleteById(Long idClass) {
        restrictionsToDelete(idClass);
        classRepository.deleteById(idClass);
    }

}
