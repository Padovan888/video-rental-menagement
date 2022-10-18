package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.repository.CustomerRepository;
import br.com.videolocadorapassatempo.repository.DependentRepository;
import br.com.videolocadorapassatempo.service.CustomerService;
import br.com.videolocadorapassatempo.service.dto.CustomerDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.CustomerMapper;
import br.com.videolocadorapassatempo.service.mapper.DependentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final DependentRepository dependentRepository;

    private final CustomerMapper customerMapper;

    private final DependentMapper dependentMapper;

    public List<CustomerDto> findAll() {
        return customerMapper.toDto(customerRepository.findAll());
    }

    public CustomerDto findById(Long idCustomer) {
        return customerMapper.toDto(customerRepository.findById(idCustomer)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer))));
    }

    private void existsCustomerById(Long idCustomer) {
        if (!customerRepository.existsById(idCustomer)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer));
        }
    }

    private void generateRegistrationNumber(CustomerDto customerDto) {
        Random random = new Random();
        customerDto.setRegistrationNumber(10000L + random.nextInt(90000));
    }

    private void setActiveCustomer(CustomerDto customerDto) {
        customerDto.setActive(true);
    }

    private void validateGender(CustomerDto customerDto) {
        Character gender = customerDto.getGender();
        if (gender != 'M' && gender != 'm' && gender != 'F' && gender != 'f') {
            throw new EntityBadRequestException("Gênero inserido inválido!");
        }
        customerDto.setGender(Character.toUpperCase(gender));
    }

    private void validateRegistrationNumber(CustomerDto customerDto) {
        if (Objects.isNull(customerDto.getRegistrationNumber())) {
            customerDto.setRegistrationNumber(customerRepository.findRegistrationNumberById(customerDto.getId()));
            return;
        }

        if (!Objects.equals(customerDto.getRegistrationNumber(), customerRepository.findRegistrationNumberById(customerDto.getId()))) {
            throw new EntityBadRequestException("O número de inscrição não pode ser alterado!");
        }
    }

    private void verifyActive(CustomerDto customerDto) {
        if (Objects.isNull(customerDto.getActive())) {
            customerDto.setActive(true);
        }
    }

    public CustomerDto create(CustomerDto customerDto) {
        validateGender(customerDto);
        generateRegistrationNumber(customerDto);
        setActiveCustomer(customerDto);
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDto)));
    }

    public CustomerDto update(CustomerDto customerDto) {
        existsCustomerById(customerDto.getId());
        validateRegistrationNumber(customerDto);
        validateGender(customerDto);
        verifyActive(customerDto);
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDto)));
    }

    private void changeLinkedDependents(CustomerDto customerDto) {
        customerDto.getDependentModel().forEach(dependent -> {
            dependent.setActive(customerDto.getActive());
            dependentRepository.save(dependentMapper.toEntity(dependent));
        });
    }

    public void changeActive(Long idCustomer) {
        CustomerDto customerDto = findById(idCustomer);
        customerDto.setActive(!customerDto.getActive());
        changeLinkedDependents(customerDto);
        customerRepository.save(customerMapper.toEntity(customerDto));
    }

    public void deleteById(Long idCustomer) {
        existsCustomerById(idCustomer);
        customerRepository.deleteById(idCustomer);
    }

}
