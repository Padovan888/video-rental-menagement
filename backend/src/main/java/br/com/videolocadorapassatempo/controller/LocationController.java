package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.LocationService;
import br.com.videolocadorapassatempo.service.dto.LocationDto;
import br.com.videolocadorapassatempo.service.dto.ViewLocationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacao")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Locação")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    @ApiOperation("Retorna todas as locações cadastradas no sistema")
    public ResponseEntity<List<ViewLocationDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll());
    }

    @GetMapping("/{idLocation}")
    @ApiOperation("Retorna uma locação cadastrada no sistema pelo id")
    public ResponseEntity<LocationDto> findById(@PathVariable Long idLocation) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findById(idLocation));
    }

    @PostMapping
    @ApiOperation("Cadastra uma locação no sistema")
    public ResponseEntity<LocationDto> create(@RequestBody LocationDto locationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(locationDto));
    }

    @PutMapping
    @ApiOperation("Atualiza uma locação no sistema")
    public ResponseEntity<LocationDto> update(@RequestBody LocationDto locationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.update(locationDto));
    }

    @DeleteMapping("/{idLocation}")
    @ApiOperation("Exclui uma locação do sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long idLocation) {
        locationService.deleteById(idLocation);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/devolucao/{serialNumber}")
    public ResponseEntity<LocationDto> makeReturn(@PathVariable String serialNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.makeReturn(serialNumber));
    }

}
