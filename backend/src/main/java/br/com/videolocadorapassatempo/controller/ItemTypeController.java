package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.ItemTypeService;
import br.com.videolocadorapassatempo.service.dto.ItemTypeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoitem")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Tipo de Item")
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @GetMapping
    @ApiOperation("Retorna todos os tipos de itens cadastrados no sistema")
    public ResponseEntity<List<ItemTypeDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(itemTypeService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um tipo de item cadastrado no sistema pelo id")
    public ResponseEntity<ItemTypeDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemTypeService.findById(id));
    }

}
