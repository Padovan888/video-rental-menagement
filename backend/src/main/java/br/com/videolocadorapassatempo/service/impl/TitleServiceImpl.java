package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.repository.ActorRepository;
import br.com.videolocadorapassatempo.repository.ClassRepository;
import br.com.videolocadorapassatempo.repository.DirectorRespository;
import br.com.videolocadorapassatempo.repository.TitleRepository;
import br.com.videolocadorapassatempo.service.TitleService;
import br.com.videolocadorapassatempo.service.dto.ActorTitleDto;
import br.com.videolocadorapassatempo.service.dto.TitleDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.TitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    private final DirectorRespository directorRespository;

    private final ClassRepository classRepository;

    private final ActorRepository actorRepository;

    private final TitleMapper titleMapper;

    public List<TitleDto> findAll() {
        return titleMapper.toDto(titleRepository.findAll());
    }

    public TitleDto findById(Long id) {
        return titleMapper.toDto(titleRepository.findById(id)
               .orElseThrow(() -> new EntityException("Título de id = " + id + " não encontrado!")));
    }

    public void existsDirectorById(Long idDirector) {
        if(!directorRespository.existsById(idDirector)) {
            throw new EntityException("Diretor de id = " + idDirector + " não encontrado!");
        }
    }

    public void existsClassById(Long idClass) {
        if(!classRepository.existsById(idClass)) {
            throw new EntityException("Classe de id = " + idClass + " não encontrada!");
        }
    }

    public void existsActorsById(List<ActorTitleDto> actorsIds) {
        actorsIds.stream().forEach(actorId -> {
            if(!actorRepository.existsById(actorId.getIdActor())) {
                throw new EntityException("Ator de id = " + actorId + " não encontrado!");
            }
        });
    }

    public void validateEntitiesById(Long idDirector, Long idClass, List<ActorTitleDto> actorsIds) {
        existsDirectorById(idDirector);
        existsClassById(idClass);
        existsActorsById(actorsIds);
    }

    public TitleDto create(TitleDto titleDto) {
        validateEntitiesById(titleDto.getIdDirector(), titleDto.getIdClass(), titleDto.getActorIds());
        return titleMapper.toDto(titleRepository.save(titleMapper.toEntity(titleDto)));
    }

    public TitleDto update(TitleDto titleDto) {
        findById(titleDto.getId());
        validateEntitiesById(titleDto.getIdDirector(), titleDto.getIdClass(), titleDto.getActorIds());
        return titleMapper.toDto(titleRepository.save(titleMapper.toEntity(titleDto)));
    }

    public void deleteById(Long id) {
        findById(id);
        titleRepository.deleteById(id);
    }

}
