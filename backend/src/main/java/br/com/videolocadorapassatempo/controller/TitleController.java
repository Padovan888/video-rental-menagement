package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.TitleService;
import br.com.videolocadorapassatempo.service.dto.CreateTitleDto;
import br.com.videolocadorapassatempo.service.dto.ViewTitleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/titulo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Título")
public class TitleController {

    private final TitleService titleService;

    @GetMapping
    @ApiOperation("Retorna todos os títulos cadastrados no sistema")
    public ResponseEntity<List<ViewTitleDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um título cadastrado no sistema pelo id")
    public ResponseEntity<ViewTitleDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um título no sistema")
    public ResponseEntity<CreateTitleDto> create(@Valid @RequestBody CreateTitleDto createTitleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(titleService.create(createTitleDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um título no sistema")
    public ResponseEntity<CreateTitleDto> update(@Valid @RequestBody CreateTitleDto createTitleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.update(createTitleDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um título do sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        titleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
