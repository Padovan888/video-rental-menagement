package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.DirectorDto;

import java.util.List;

public interface DirectorService {

    public List<DirectorDto> findAll();

    public DirectorDto findById(Long id);

    public DirectorDto create(DirectorDto directorDto);

    public DirectorDto update(DirectorDto directorDto);

    public void deleteById(Long id);

}
