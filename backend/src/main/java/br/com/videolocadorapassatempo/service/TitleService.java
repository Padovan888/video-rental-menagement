package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.TitleDto;

import java.util.List;

public interface TitleService {

    public List<TitleDto> findAll();

    public TitleDto findById(Long id);

    public TitleDto create(TitleDto titleDto);

    public TitleDto update(TitleDto titleDto);

    public void deleteById(Long id);

}
