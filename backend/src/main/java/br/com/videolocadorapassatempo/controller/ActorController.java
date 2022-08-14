package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/ator")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Ator")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    @ApiOperation("Retorna todos os atores cadastrados no sistema")
    public ResponseEntity<List<ActorDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um ator cadastrado no sistema pelo seu id")
    public ResponseEntity<ActorDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um ator no sistema")
    public ResponseEntity<ActorDto> create(@Valid @RequestBody ActorDto actorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.create(actorDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um ator no sistema")
    public ResponseEntity<ActorDto> update(@Valid @RequestBody ActorDto actorDto) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.update(actorDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um ator do sistema")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        actorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Autor deletado com sucesso!");
    }

}
