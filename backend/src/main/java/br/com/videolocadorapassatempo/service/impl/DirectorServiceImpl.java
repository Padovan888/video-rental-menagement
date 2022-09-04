package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.DirectorModel;
import br.com.videolocadorapassatempo.repository.DirectorRespository;
import br.com.videolocadorapassatempo.repository.TitleRepository;
import br.com.videolocadorapassatempo.service.DirectorService;
import br.com.videolocadorapassatempo.service.dto.DirectorDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.DirectorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRespository directorRespository;

    private final TitleRepository titleRepository;

    private final DirectorMapper directorMapper;

    @Transactional(readOnly = true)
    public List<DirectorDto> findAll() {
        return directorMapper.toDto(directorRespository.findAll());
    }

    @Transactional(readOnly = true)
    public DirectorDto findById(Long idDirector) {
        DirectorModel directorModel = directorRespository.findById(idDirector)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), idDirector)));

        return directorMapper.toDto(directorModel);
    }

    public DirectorDto create(DirectorDto directorDto) {
        DirectorModel directorModel = directorMapper.toEntity(directorDto);
        return directorMapper.toDto(directorRespository.save(directorModel));
    }

    public DirectorDto update(DirectorDto directorDto) {
        if(!directorRespository.existsById(directorDto.getId())) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), directorDto.getId()));
        }

        return directorMapper.toDto(directorRespository.save(directorMapper.toEntity(directorDto)));
    }

    @Transactional(readOnly = true)
    private void restrictionsToDelete(Long idDirector) {
        if(!directorRespository.existsById(idDirector)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), idDirector));
        }

        if(titleRepository.existsByDirectorModelId(idDirector)) {
            throw new EntityBadRequestException(EntityBadRequestException.getMessageErrorEntityLink(Entity.DIRECTOR.getName(), Entity.TITLE.getName(), idDirector));
        }
    }

    public void deleteById(Long idDirector) {
        restrictionsToDelete(idDirector);
        directorRespository.deleteById(idDirector);
    }

}
