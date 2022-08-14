package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.DirectorModel;
import br.com.videolocadorapassatempo.repository.DirectorRespository;
import br.com.videolocadorapassatempo.service.DirectorService;
import br.com.videolocadorapassatempo.service.dto.DirectorDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.DirectorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRespository directorRespository;

    private final DirectorMapper directorMapper;

    public List<DirectorDto> findAll() {
        log.info("Listando todos os diretores cadastrados no sistema");
        return directorMapper.toDto(directorRespository.findAll());
    }

    public DirectorDto findById(Long id) {
        log.info("Buscando diretor de id = {}", id);
        Optional<DirectorModel> directorModel = directorRespository.findById(id);

        if(!directorModel.isPresent()) {
            log.info("Diretor de id = {} não encontrado", id);
            throw new EntityException("Diretor não encontrado!");
        }

        log.info("Diretor de id = {} encontrado com sucesso!");
        return directorMapper.toDto(directorModel.get());
    }

    public DirectorDto create(DirectorDto directorDto) {
        log.info("Diretor cadastrado com sucesso!");
        DirectorModel directorModel = directorMapper.toEntity(directorDto);
        return directorMapper.toDto(directorRespository.save(directorModel));
    }

    public DirectorDto update(DirectorDto directorDto) {
        if(!directorRespository.existsById(directorDto.getId())) {
            log.info("Diretor de id = {} não encontrado", directorDto.getId());
            throw new EntityException("Diretor não encontrado!");
        }

        log.info("Diretor de id = {} atualizado com sucesso", directorDto.getId());
        DirectorModel directorModel = directorMapper.toEntity(directorDto);
        return directorMapper.toDto(directorRespository.save(directorModel));
    }

    public void deleteById(Long id) {
        if(!directorRespository.existsById(id)) {
            log.info("Diretor de id = {} não encontrado", id);
            throw new EntityException("Diretor não encontrado!");
        }

        log.info("Diretor de id = {} deletado com sucesso", id);
        directorRespository.deleteById(id);
    }

}
