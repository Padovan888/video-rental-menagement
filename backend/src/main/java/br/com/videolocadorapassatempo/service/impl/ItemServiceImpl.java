package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ItemModel;
import br.com.videolocadorapassatempo.repository.ItemRepository;
import br.com.videolocadorapassatempo.repository.ItemTypeRepository;
import br.com.videolocadorapassatempo.repository.TitleRepository;
import br.com.videolocadorapassatempo.service.ItemService;
import br.com.videolocadorapassatempo.service.dto.ItemDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final TitleRepository titleRepository;

    private final ItemTypeRepository itemTypeRepository;

    private final ItemMapper itemMapper;

    public List<ItemDto> findAll() {
        return itemMapper.toDto(itemRepository.findAll());
    }

    public ItemDto findById(Long id) {
        Optional<ItemModel> itemModel = itemRepository.findById(id);

        if(!itemModel.isPresent()) {
            throw new EntityException("Item de id = " + id + " não encontrado!");
        }

        return itemMapper.toDto(itemModel.get());
    }

    public void existsTitleById(Long idTitle) {
        if(!titleRepository.existsById(idTitle)) {
            throw new EntityException("Título de id = " + idTitle + " não encontrado!");
        }
    }

    public void existsItemTypeById(Long idItemType) {
        if(!itemTypeRepository.existsById(idItemType)) {
            throw new EntityException("Tipo de item de id = " + idItemType + " não encontrado!");
        }
    }

    public void existsItemById(Long id) {
        if(!itemRepository.existsById(id)) {
            throw new EntityException("Item de id = " + id + " não encontrado!");
        }
    }

    public ItemDto create(ItemDto itemDto) {
        existsTitleById(itemDto.getIdTitle());
        existsItemTypeById(itemDto.getIdItemType());
        return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(itemDto)));
    }

    public ItemDto update(ItemDto itemDto) {
        existsItemById(itemDto.getId());
        existsTitleById(itemDto.getIdTitle());
        existsItemTypeById(itemDto.getIdItemType());
        return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(itemDto)));
    }

    public void deleteById(Long id) {
        existsItemById(id);
        itemRepository.deleteById(id);
    }

}
