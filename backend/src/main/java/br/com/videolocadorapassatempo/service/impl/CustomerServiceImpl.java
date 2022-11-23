package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.CustomerModel;
import br.com.videolocadorapassatempo.repository.CustomerRepository;
import br.com.videolocadorapassatempo.service.CustomerService;
import br.com.videolocadorapassatempo.service.dto.*;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.CreateDependentMapper;
import br.com.videolocadorapassatempo.service.mapper.CreateMemberMapper;
import br.com.videolocadorapassatempo.service.mapper.ViewCustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ViewCustomerMapper viewCustomerMapper;

    private final CreateMemberMapper createMemberMapper;

    private final CreateDependentMapper createDependentMapper;

    public List<ViewCustomerDto> findAll() {
        return viewCustomerMapper.toDto(customerRepository.findAll());
    }

    public List<ViewCustomerDto> findAllActive() {
        return customerRepository.findAllActive();
    }

    public FindByIdCustomerDto findById(Long idCustomer) {
        existsCustomerById(idCustomer);
        FindByIdCustomerDto findByIdCustomerDto = new FindByIdCustomerDto();
        CustomerDto customerDto = customerRepository.findByIdCustomerDto(idCustomer);
        BeanUtils.copyProperties(customerDto, findByIdCustomerDto);
        fillDependents(findByIdCustomerDto);
        return findByIdCustomerDto;
    }

    private void existsCustomerById(Long idCustomer) {
        if (!customerRepository.existsById(idCustomer)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer));
        }
    }

    private void fillDependents(FindByIdCustomerDto findByIdCustomerDto) {
        if (Objects.equals(findByIdCustomerDto.getType(), "D")) {
            return;
        }
        List<CustomerDto> dependents = customerRepository.findDependentsByidMember(findByIdCustomerDto.getId());
        findByIdCustomerDto.setDependents(dependents);
    }

    private Long generateRegistrationNumber() {
        Random random = new Random();
        return 10000L + random.nextInt(90000);
    }

    private void validateGender(Character gender) {
        if (gender != 'M' && gender != 'm' && gender != 'F' && gender != 'f') {
            throw new EntityBadRequestException("Gênero inserido inválido!");
        }
    }

    private void validateRegistrationNumberMember(CreateMemberDto createMemberDto) {
        if (Objects.isNull(createMemberDto.getRegistrationNumber())) {
            createMemberDto.setRegistrationNumber(customerRepository.findRegistrationNumberById(createMemberDto.getId()));
            return;
        }

        if (!Objects.equals(createMemberDto.getRegistrationNumber(), customerRepository.findRegistrationNumberById(createMemberDto.getId()))) {
            throw new EntityBadRequestException("O número de inscrição não pode ser alterado!");
        }
    }

    private void validateRegistrationNumberDependent(CreateDependentDto createDependentDto) {
        if (Objects.isNull(createDependentDto.getRegistrationNumber())) {
            createDependentDto.setRegistrationNumber(customerRepository.findRegistrationNumberById(createDependentDto.getId()));
            return;
        }

        if (!Objects.equals(createDependentDto.getRegistrationNumber(), customerRepository.findRegistrationNumberById(createDependentDto.getId()))) {
            throw new EntityBadRequestException("O número de inscrição não pode ser alterado!");
        }
    }

    private void existsMemberById(Long idMember) {
        if (!customerRepository.existsMemberById(idMember)) {
            throw new EntityNotFoundException("Não existe membro com esse id");
        }
    }

    private void existsDependentById(Long idDependent) {
        if (!customerRepository.existsDependentById(idDependent)) {
            throw new EntityNotFoundException("Não existe dependente com esse id");
        }
    }

    private void checkPossibilityCreateDependent(Long idMember) {
        if (customerRepository.checkPossibilityCreateDependent(idMember)) {
            throw new EntityBadRequestException("Este membro já possui três dependente vinculados!");
        }
    }

    public CreateMemberDto createMember(CreateMemberDto createMemberDto) {
        createMemberDto.setType("M");
        validateGender(createMemberDto.getGender());
        createMemberDto.setGender(Character.toUpperCase(createMemberDto.getGender()));
        createMemberDto.setRegistrationNumber(generateRegistrationNumber());
        createMemberDto.setActive(true);
        return createMemberMapper.toDto(customerRepository.save(createMemberMapper.toEntity(createMemberDto)));
    }

    public CreateMemberDto updateMember(CreateMemberDto createMemberDto) {
        existsMemberById(createMemberDto.getId());
        createMemberDto.setType("M");
        validateRegistrationNumberMember(createMemberDto);
        validateGender(createMemberDto.getGender());
        createMemberDto.setGender(Character.toUpperCase(createMemberDto.getGender()));
        if (Objects.isNull(createMemberDto.getActive())) {
            createMemberDto.setActive(true);
        }
        return createMemberMapper.toDto(customerRepository.save(createMemberMapper.toEntity(createMemberDto)));
    }

    public CreateDependentDto createDependent(CreateDependentDto createDependentDto) {
        checkPossibilityCreateDependent(createDependentDto.getIdMember());
        existsMemberById(createDependentDto.getIdMember());
        createDependentDto.setType("D");
        validateGender(createDependentDto.getGender());
        createDependentDto.setGender(Character.toUpperCase(createDependentDto.getGender()));
        createDependentDto.setRegistrationNumber(generateRegistrationNumber());
        createDependentDto.setActive(true);
        return createDependentMapper.toDto(customerRepository.save(createDependentMapper.toEntity(createDependentDto)));
    }

    public CreateDependentDto updateDependent(CreateDependentDto createDependentDto) {
        existsDependentById(createDependentDto.getId());
        existsMemberById(createDependentDto.getIdMember());
        createDependentDto.setType("D");
        validateRegistrationNumberDependent(createDependentDto);
        validateGender(createDependentDto.getGender());
        createDependentDto.setGender(Character.toUpperCase(createDependentDto.getGender()));
        if (Objects.isNull(createDependentDto.getActive())) {
            createDependentDto.setActive(true);
        }
        return createDependentMapper.toDto(customerRepository.save(createDependentMapper.toEntity(createDependentDto)));
    }

    public void changeActive(Long idCustomer) {
        if (!customerRepository.existsById(idCustomer)) {
            throw new EntityNotFoundException("Cliente de id = " + idCustomer + " não existe!");
        }
        Optional<CustomerModel> customerModel = customerRepository.findById(idCustomer);
        if (customerRepository.existsMemberById(idCustomer)) {
            customerRepository.changeActiveMember(idCustomer, !customerModel.get().getActive());
            return;
        }
        customerRepository.changeActiveDependent(idCustomer, !customerModel.get().getActive());
    }

    public void deleteById(Long idCustomer) {
        existsCustomerById(idCustomer);
        if (customerRepository.existsMemberById(idCustomer)) {
            customerRepository.deleteDependentsByIdMember(idCustomer);
            customerRepository.deleteById(idCustomer);
            return;
        }
        customerRepository.deleteById(idCustomer);
    }

    @Override
    public List<ViewMemberDto> findAllMembers() {
        List<CustomerModel> members = customerRepository.findAllByType("M");
        System.out.println(members.size());
        return fillAllDependents(members); 
    }

    private List<ViewMemberDto> fillAllDependents(List<CustomerModel> members) {
        List<ViewMemberDto> list = new ArrayList<>();
        for (CustomerModel item : members) {
            ViewMemberDto res = new ViewMemberDto();
            BeanUtils.copyProperties(item, res);
            List<CustomerDto> dependents = customerRepository.findDependentsByidMember(item.getId());
            res.setDependents(dependents);
            list.add(res);
        }
        return list;
    }

}
