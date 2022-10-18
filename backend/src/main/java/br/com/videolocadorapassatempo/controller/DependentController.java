package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.DependentService;
import br.com.videolocadorapassatempo.service.dto.DependentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dependente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Dependente")
public class DependentController {

    private final DependentService dependentService;

    @GetMapping
    @ApiOperation("Retorna todos os dependentes cadastrados no sistema")
    public ResponseEntity<List<DependentDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(dependentService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um dependente cadastrado no sistema pelo id")
    public ResponseEntity<DependentDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dependentService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um dependente no sistema")
    public ResponseEntity<DependentDto> create(@Valid @RequestBody DependentDto dependentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dependentService.create(dependentDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um dependente no sistema")
    public ResponseEntity<DependentDto> update(@Valid @RequestBody DependentDto dependentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(dependentService.update(dependentDto));
    }

    @PatchMapping("/{id}")
    @ApiOperation("Altera o atributo ativo de um dependente cadastrado no sistema pelo id")
    public ResponseEntity<Void> changeActive(@PathVariable Long id) {
        dependentService.changeActive(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um dependente do sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        dependentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
