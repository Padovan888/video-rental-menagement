package br.com.videolocadorapassatempo.controller;

import br.com.videolocadorapassatempo.service.CustomerService;
import br.com.videolocadorapassatempo.service.dto.CreateDependentDto;
import br.com.videolocadorapassatempo.service.dto.CreateMemberDto;
import br.com.videolocadorapassatempo.service.dto.FindByIdCustomerDto;
import br.com.videolocadorapassatempo.service.dto.ViewCustomerDto;
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
    public ResponseEntity<List<ViewCustomerDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/ativo")
    @ApiOperation("Retorna todos os clientes ativos cadastrados no sistema")
    public ResponseEntity<List<ViewCustomerDto>> findAllActive() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllActive());
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um cliente cadastrado no sistema pelo id")
    public ResponseEntity<FindByIdCustomerDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    @PostMapping("/membro")
    @ApiOperation("Cadastra um membro no sistema")
    public ResponseEntity<CreateMemberDto> createMember(@Valid @RequestBody CreateMemberDto createMemberDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createMember(createMemberDto));
    }

    @PutMapping("/membro")
    @ApiOperation("Atualiza um membro no sistema")
    public ResponseEntity<CreateMemberDto> updateMember(@Valid @RequestBody CreateMemberDto createMemberDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateMember(createMemberDto));
    }

    @PostMapping("/dependente")
    @ApiOperation("Cadastra um dependente no sistema")
    public ResponseEntity<CreateDependentDto> createMember(@Valid @RequestBody CreateDependentDto createDependentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createDependent(createDependentDto));
    }

    @PutMapping("/dependente")
    @ApiOperation("Atualiza um dependente no sistema")
    public ResponseEntity<CreateDependentDto> updateMember(@Valid @RequestBody CreateDependentDto createDependentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateDependent(createDependentDto));
    }

    @PatchMapping("/{idCustomer}")
    @ApiOperation("Altera o atribudo ativo de um cliente cadastrado no sistema pelo id")
    public ResponseEntity<Void> changeActive(@PathVariable Long idCustomer) {
        customerService.changeActive(idCustomer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um cliente do sistema pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
