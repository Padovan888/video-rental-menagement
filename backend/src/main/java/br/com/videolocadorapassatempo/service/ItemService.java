package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.ItemDto;

import java.util.List;

public interface ItemService {

    public List<ItemDto> findAll();

    public ItemDto findById(Long id);

    public ItemDto create(ItemDto itemDto);

    public ItemDto update(ItemDto itemDto);

    public void deleteById(Long id);

}
