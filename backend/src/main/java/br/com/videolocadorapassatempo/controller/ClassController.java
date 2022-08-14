package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.ClassService;
import br.com.videolocadorapassatempo.service.dto.ClassDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classe")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Classe")
public class ClassController {

    private final ClassService classService;

    @GetMapping
    @ApiOperation("Retorna todas as classes cadastradas no sistema")
    public ResponseEntity<List<ClassDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna uma classe cadastrada no sistema pelo id")
    public ResponseEntity<ClassDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra uma classe no sistema")
    public ResponseEntity<ClassDto> create(@Valid @RequestBody ClassDto classDto) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.create(classDto));
    }

    @PutMapping
    @ApiOperation("Atualiza uma classe no sistema")
    public ResponseEntity<ClassDto> update(@Valid @RequestBody ClassDto classDto) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.update(classDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui uma classe do sistema")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        classService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Classe deletada com sucesso!");
    }

}
