package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.TitleDto;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;

import java.util.List;

public interface TitleService {

    public List<ViewTitleDto> findAll();

    public ViewTitleDto findById(Long id);

    public TitleDto create(TitleDto titleDto);

    public TitleDto update(TitleDto titleDto);

    public void deleteById(Long id);

}
