package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ClassModel;
import br.com.videolocadorapassatempo.repository.ClassRepository;
import br.com.videolocadorapassatempo.service.ClassService;
import br.com.videolocadorapassatempo.service.dto.ClassDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    private final ClassMapper classMapper;

    public List<ClassDto> findAll() {
        log.info("Listando todas as classes cadastradas no sistema");
        return classMapper.toDto(classRepository.findAll());
    }

    public ClassDto findById(Long id) {
        log.info("Buscando classe de id = {}", id);
        Optional<ClassModel> classModel = classRepository.findById(id);

        if(!classModel.isPresent()) {
            log.info("Classe de id = {} não encontrada", id);
            throw new EntityException("Classe de id = " + id + " não encontrada!");
        }

        log.info("Classe de id = {} encontrada com sucesso");
        return classMapper.toDto(classModel.get());
    }

    public ClassDto create(ClassDto classDto) {
        log.info("Classe cadastrada com sucesso!");
        ClassModel classModel = classMapper.toEntity(classDto);
        return classMapper.toDto(classRepository.save(classModel));
    }

    public ClassDto update(ClassDto classDto) {
        if(!classRepository.existsById(classDto.getId())) {
            log.info("Classe de id = {} não encontrada", classDto.getId());
            throw new EntityException("Classe de id = " + classDto.getId() + " não encontrada!");
        }

        log.info("Classe de id = {} atualizada com sucesso", classDto.getId());
        ClassModel classModel = classMapper.toEntity(classDto);
        return classMapper.toDto(classRepository.save(classModel));
    }

    public void deleteById(Long id) {
        if(!classRepository.existsById(id)) {
            log.info("Classe de id = {} não encontrada", id);
            throw new EntityException("Classe de id = " + id + " não encontrada!");
        }

        log.info("Classe de id = {} deletada com sucesso", id);
        classRepository.deleteById(id);
    }

}
