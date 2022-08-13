package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.repository.ActorRepository;
import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final ActorMapper actorMapper;

    public List<ActorDto> findAll() {
        return actorMapper.toDto(actorRepository.findAll());
    }

    public ActorDto findById(Long id) {
        log.info("Buscando ator de id = {}", id);
        ActorModel actorModel = actorRepository.findById(id)
                .orElseThrow(() -> new EntityException("Autor de id = " + id + " não encontrado!"));

        log.info("Ator de id = {} encontrado com sucesso", id);
        return actorMapper.toDto(actorModel);
    }

    public ActorDto create(ActorDto actorDto) {
        log.info("Ator cadastrado com sucesso!");
        ActorModel actorModel = actorMapper.toEntity(actorDto);
        return actorMapper.toDto(actorRepository.save(actorModel));
    }

    public ActorDto update(ActorDto actorDto) {
        log.info("Buscando ator de id = {}", actorDto.getId());
        ActorModel actorModel = actorRepository.findById(actorDto.getId())
                .orElseThrow(() -> new EntityException("Autor de id = " + actorDto.getId() + " não encontrado!"));

        actorModel.setName(actorDto.getName());
        log.info("Ator de id = {} atualizado com sucesso!", actorDto.getId());
        return actorMapper.toDto(actorRepository.save(actorModel));
    }

    public void deleteById(Long id) {
        log.info("Buscando ator de id = {}", id);
        if(!actorRepository.existsById(id)) {
            throw new EntityException("Autor com id = " + id + " não encontrado!");
        }

        log.info("Ator de id = {} deletado com sucesso!", id);
        actorRepository.deleteById(id);
    }

}
