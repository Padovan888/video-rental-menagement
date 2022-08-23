package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.CreateTitleDto;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;

import java.util.List;

public interface TitleService {

    public List<ViewTitleDto> findAll();

    public ViewTitleDto findById(Long id);

    public CreateTitleDto create(CreateTitleDto createTitleDto);

    public CreateTitleDto update(CreateTitleDto createTitleDto);

    public void deleteById(Long id);

}
