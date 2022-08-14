package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.ClassDto;

import java.util.List;

public interface ClassService {

    public List<ClassDto> findAll();

    public ClassDto findById(Long id);

    public ClassDto create(ClassDto classDto);

    public ClassDto update(ClassDto classDto);

    public void deleteById(Long id);

}
