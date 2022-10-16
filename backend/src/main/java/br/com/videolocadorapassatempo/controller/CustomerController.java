package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.CustomerService;
import br.com.videolocadorapassatempo.service.dto.CustomerDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(tags = "Cliente")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation("Retorna todos os clientes cadastrados no sistema")
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um cliente cadastrado no sistema pelo id")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    @PostMapping
    @ApiOperation("Cadastra um cliente no sistema")
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerDto));
    }

    @PutMapping
    @ApiOperation("Atualiza um cliente no sistema")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(customerDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um cliente do sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
