package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ItemTypeModel;
import br.com.videolocadorapassatempo.repository.ItemTypeRepository;
import br.com.videolocadorapassatempo.service.ItemTypeService;
import br.com.videolocadorapassatempo.service.dto.ItemTypeDto;
import br.com.videolocadorapassatempo.service.exception.EntityException;
import br.com.videolocadorapassatempo.service.mapper.ItemTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    private final ItemTypeMapper itemTypeMapper;

    public List<ItemTypeDto> findAll() {
        return itemTypeMapper.toDto(itemTypeRepository.findAll());
    }

    public ItemTypeDto findById(Long id) {
        Optional<ItemTypeModel> itemTypeModel = itemTypeRepository.findById(id);

        if(!itemTypeModel.isPresent()) {
            throw new EntityException("O tipo de item de id = " + id + " não foi encontrado!");
        }

        return itemTypeMapper.toDto(itemTypeModel.get());
    }

}
