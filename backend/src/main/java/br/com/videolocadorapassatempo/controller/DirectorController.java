package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.DirectorService;
import br.com.videolocadorapassatempo.service.dto.DirectorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/diretor")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Diretor")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @ApiOperation("Retorna todos os diretores cadastrados no sistema")
    public ResponseEntity<List<DirectorDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(directorService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um diretor cadastrado no sistema pelo id")
    public ResponseEntity<DirectorDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(directorService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um diretor no sistema")
    public ResponseEntity<DirectorDto> create(@Valid @RequestBody DirectorDto directorDto) {
        return ResponseEntity.status(HttpStatus.OK).body(directorService.create(directorDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um diretor no sistema")
    public ResponseEntity<DirectorDto> update(@Valid @RequestBody DirectorDto directorDto) {
        return ResponseEntity.status(HttpStatus.OK).body(directorService.update(directorDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um diretor do sistema")
    public ResponseEntity deleteById(@PathVariable Long id) {
        directorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
