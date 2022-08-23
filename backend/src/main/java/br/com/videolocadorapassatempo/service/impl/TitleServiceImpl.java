package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ActorModel;
import br.com.videolocadorapassatempo.model.TitleActorModel;
import br.com.videolocadorapassatempo.model.TitleModel;
import br.com.videolocadorapassatempo.model.id.TitleActorId;
import br.com.videolocadorapassatempo.repository.*;
import br.com.videolocadorapassatempo.service.TitleService;
import br.com.videolocadorapassatempo.service.dto.CreateTitleDto;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.CreateTitleMapper;
import br.com.videolocadorapassatempo.service.mapper.ViewTitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    private final TitleActorRepository titleActorRepository;

    private final ActorRepository actorRepository;

    private final DirectorRespository directorRespository;

    private final ClassRepository classRepository;

    private final ViewTitleMapper viewTitleMapper;

    private final CreateTitleMapper createTitleMapper;

    public List<ViewTitleDto> findAll() {
        return viewTitleMapper.toDto(titleRepository.findAll());
    }

    public ViewTitleDto findById(Long id) {
        Optional<TitleModel> titleModel = titleRepository.findById(id);

        if(!titleModel.isPresent()) {
            throw new EntityException("Título com id = " + id + " não encontrado");
        }

        return viewTitleMapper.toDto(titleModel.get());
    }

    public TitleActorId createIdTitleActor(Long idTitle, Long idActor) {
        TitleActorId titleActorId = new TitleActorId();
        titleActorId.setTitleId(idTitle);
        titleActorId.setActorId(idActor);
        return titleActorId;
    }

    public ActorModel findActorModelById(Long id) {
        Optional<ActorModel> actorModel = actorRepository.findById(id);

        if(!actorModel.isPresent()) {
            throw new EntityException("Ator de id = " + id + " não encontrado!");
        }

        return actorModel.get();
    }

    public void existsDirectorById(Long directorId) {
        if(!directorRespository.existsById(directorId)) {
            throw new EntityException("Diretor de id = " + directorId + " não encontrado!");
        }
    }

    public void existsClassById(Long classId) {
        if(!classRepository.existsById(classId)) {
            throw new EntityException("Classe de id = " + classId + " não encontrada!");
        }
    }

    public void verifyDirectorAndClassExists(Long directorId, Long classId) {
        existsDirectorById(directorId);
        existsClassById(classId);
    }

    public void verifyTitleExists(Long titleId) {
        if(!titleRepository.existsById(titleId)) {
            throw new EntityException("Título de id = " + titleId + " não encontrado!");
        }
    }

    public TitleActorModel createTitleActorModel(TitleActorId titleActorId, TitleModel titleModel, ActorModel actorModel) {
        TitleActorModel titleActorModel = new TitleActorModel();
        titleActorModel.setId(titleActorId);
        titleActorModel.setTitleModel(titleModel);
        titleActorModel.setActorModel(actorModel);
        return titleActorModel;
    }

    @Transactional
    public void persistInTitleActorModel(List<Long> actorIds, TitleModel titleModel) {
        List<TitleActorModel> titleActorModelList = actorIds.stream().map(actorId -> {
            ActorModel actorModel = findActorModelById(actorId);
            TitleActorId titleActorId = createIdTitleActor(titleModel.getId(), actorModel.getId());
            return createTitleActorModel(titleActorId, titleModel, actorModel);
        }).collect(Collectors.toList());
        titleActorRepository.saveAll(titleActorModelList);
    }

    @Transactional
    public CreateTitleDto create(CreateTitleDto createTitleDto) {
        verifyDirectorAndClassExists(createTitleDto.getIdDirector(), createTitleDto.getIdClass());
        TitleModel titleModel = titleRepository.save(createTitleMapper.toEntity(createTitleDto));
        persistInTitleActorModel(createTitleDto.getActorIds(), titleModel);
        return createTitleMapper.toDto(titleModel);
    }

    @Transactional
    public CreateTitleDto update(CreateTitleDto createTitleDto) {
        verifyDirectorAndClassExists(createTitleDto.getIdDirector(), createTitleDto.getIdClass());
        verifyTitleExists(createTitleDto.getId());
        TitleModel titleModel = titleRepository.save(createTitleMapper.toEntity(createTitleDto));
        titleActorRepository.deleteAllByTitleId(titleModel.getId());
        persistInTitleActorModel(createTitleDto.getActorIds(), titleModel);
        return createTitleMapper.toDto(titleModel);
    }

    @Transactional
    public void deleteById(Long titleId) {
        verifyTitleExists(titleId);
        titleActorRepository.deleteAllByTitleId(titleId);
        titleRepository.deleteById(titleId);
    }

}
