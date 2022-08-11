package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.repository.ActorRepository;
import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import br.com.videolocadorapassatempo.service.exception.ActorException;
import br.com.videolocadorapassatempo.service.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<ActorModel> actorModel = actorRepository.findById(id);

        if(!actorModel.isPresent()) {
            throw new ActorException("Autor não encontrado");
        }

        return actorMapper.toDto(actorModel.get());
    }

    public ActorDto save(ActorDto actorDto) {
        ActorModel actorModel = actorMapper.toEntity(actorDto);
        return actorMapper.toDto(actorRepository.save(actorModel));
    }

    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }

}
