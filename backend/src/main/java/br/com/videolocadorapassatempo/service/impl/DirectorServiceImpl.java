package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.DirectorModel;
import br.com.videolocadorapassatempo.repository.DirectorRepository;
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

    private final DirectorRepository directorRepository;

    private final TitleRepository titleRepository;

    private final DirectorMapper directorMapper;

    @Transactional(readOnly = true)
    public List<DirectorDto> findAll() {
        return directorMapper.toDto(directorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public DirectorDto findById(Long idDirector) {
        DirectorModel directorModel = directorRepository.findById(idDirector)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), idDirector)));

        return directorMapper.toDto(directorModel);
    }

    public DirectorDto create(DirectorDto directorDto) {
        return directorMapper.toDto(directorRepository.save(directorMapper.toEntity(directorDto)));
    }

    public DirectorDto update(DirectorDto directorDto) {
        if(!directorRepository.existsById(directorDto.getId())) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), directorDto.getId()));
        }

        return directorMapper.toDto(directorRepository.save(directorMapper.toEntity(directorDto)));
    }

    @Transactional(readOnly = true)
    private void restrictionsToDelete(Long idDirector) {
        if(!directorRepository.existsById(idDirector)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DIRECTOR.getName(), idDirector));
        }

        if(titleRepository.existsByDirectorModelId(idDirector)) {
            throw new EntityBadRequestException(EntityBadRequestException.getMessageErrorEntityLink(Entity.DIRECTOR.getName(), Entity.TITLE.getName(), idDirector));
        }
    }

    public void deleteById(Long idDirector) {
        restrictionsToDelete(idDirector);
        directorRepository.deleteById(idDirector);
    }

}
