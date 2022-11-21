package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.CreateDependentDto;
import br.com.videolocadorapassatempo.service.dto.CreateMemberDto;
import br.com.videolocadorapassatempo.service.dto.FindByIdCustomerDto;
import br.com.videolocadorapassatempo.service.dto.ViewCustomerDto;

import java.util.List;

public interface CustomerService {

    List<ViewCustomerDto> findAll();

    FindByIdCustomerDto findById(Long idCustomer);

    CreateMemberDto createMember(CreateMemberDto createMemberDto);

    CreateMemberDto updateMember(CreateMemberDto createMemberDto);

    CreateDependentDto createDependent(CreateDependentDto createDependentDto);

    CreateDependentDto updateDependent(CreateDependentDto createDependentDto);

    void changeActive(Long idCustomer);

    void deleteById(Long idCustomer);

}
