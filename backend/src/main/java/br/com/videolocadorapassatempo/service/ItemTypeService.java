package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.ItemTypeDto;

import java.util.List;

public interface ItemTypeService {

    public List<ItemTypeDto> findAll();

    public ItemTypeDto findById(Long id);

}
