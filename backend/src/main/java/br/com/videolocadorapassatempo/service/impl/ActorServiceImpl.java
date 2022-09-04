package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.repository.ActorRepository;
import br.com.videolocadorapassatempo.repository.TitleRepository;
import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final TitleRepository titleRepository;

    private final ActorMapper actorMapper;

    @Transactional(readOnly = true)
    public List<ActorDto> findAll() {
        return actorMapper.toDto(actorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ActorDto findById(Long idActor) {
        ActorModel actorModel = actorRepository.findById(idActor)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.ACTOR.getName(), idActor)));

        return actorMapper.toDto(actorModel);
    }

    public ActorDto create(ActorDto actorDto) {
        ActorModel actorModel = actorMapper.toEntity(actorDto);
        return actorMapper.toDto(actorRepository.save(actorModel));
    }

    public ActorDto update(ActorDto actorDto) {
        if(!actorRepository.existsById(actorDto.getId())) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.ACTOR.getName(), actorDto.getId()));
        }

        return actorMapper.toDto(actorRepository.save(actorMapper.toEntity(actorDto)));
    }

    @Transactional(readOnly = true)
    private void restrictionsToDelete(Long idActor) {
        if(!actorRepository.existsById(idActor)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.ACTOR.getName(), idActor));
        }

        if(titleRepository.existsByIdActor(idActor)) {
            throw new EntityBadRequestException(EntityBadRequestException.getMessageErrorEntityLink(Entity.ACTOR.getName(), Entity.TITLE.getName(), idActor));
        }
    }

    public void deleteById(Long idActor) {
        restrictionsToDelete(idActor);
        actorRepository.deleteById(idActor);
    }

}
