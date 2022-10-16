package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.DependentDto;

import java.util.List;

public interface DependentService {

    List<DependentDto> findAll();

    DependentDto findById(Long idDependent);

    DependentDto create(DependentDto dependentDto);

    DependentDto update(DependentDto dependentDto);

    void deleteById(Long idDependent);

}
