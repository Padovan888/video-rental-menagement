package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.ItemService;
import br.com.videolocadorapassatempo.service.dto.ItemDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @ApiOperation("Retorna todos os itens cadastrados no sistema")
    public ResponseEntity<List<ItemDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um item cadastrado no sistema pelo id")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um item no sistema")
    public ResponseEntity<ItemDto> create(@Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(itemDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um item cadastrado no sistema")
    public ResponseEntity<ItemDto> update(@Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.update(itemDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um item cadastrado no sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
