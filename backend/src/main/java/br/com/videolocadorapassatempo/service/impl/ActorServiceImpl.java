package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.repository.ActorRepository;
import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import br.com.videolocadorapassatempo.service.exception.ActorException;
import br.com.videolocadorapassatempo.service.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final ActorMapper actorMapper;

    public List<ActorDto> findAll() {
        return actorMapper.toDto(actorRepository.findAll());
    }

    public ActorDto findById(UUID id) {
        Optional<ActorModel> authorModel = actorRepository.findById(id);

        if(!authorModel.isPresent()) {
            new ActorException("Conflito: O autor não existe!");
        }

        return actorMapper.toDto(authorModel.get());
    }

    public ActorDto save(ActorDto actorDto) {
        ActorModel actorModel = actorMapper.toEntity(actorDto);
        return actorMapper.toDto(actorRepository.save(actorModel));
    }

    public void deleteById(UUID id) {
        actorRepository.deleteById(id);
    }

}
