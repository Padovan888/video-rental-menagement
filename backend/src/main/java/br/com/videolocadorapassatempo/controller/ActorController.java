package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.ActorService;
import br.com.videolocadorapassatempo.service.dto.ActorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ator")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findAll());
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActorDto> save(@Valid @RequestBody ActorDto actorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(actorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body("Author successfully deleted!");
    }

}
