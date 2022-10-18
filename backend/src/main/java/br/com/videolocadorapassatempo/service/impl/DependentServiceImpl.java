package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.repository.CustomerRepository;
import br.com.videolocadorapassatempo.repository.DependentRepository;
import br.com.videolocadorapassatempo.service.DependentService;
import br.com.videolocadorapassatempo.service.dto.DependentDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.DependentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DependentServiceImpl implements DependentService {

    private final Integer MAX_DEPENDENTS = 3;

    private final DependentRepository dependentRepository;

    private final CustomerRepository customerRepository;

    private final DependentMapper dependentMapper;

    public List<DependentDto> findAll() {
        return dependentMapper.toDto(dependentRepository.findAll());
    }

    public DependentDto findById(Long idDependent) {
        return dependentMapper.toDto(dependentRepository.findById(idDependent)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DEPENDENT.getName(), idDependent))));
    }

    private void existsDependentById(Long idDependent) {
        if (!dependentRepository.existsById(idDependent)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DEPENDENT.getName(), idDependent));
        }
    }

    private void verifyIdCustomerIsNull(DependentDto dependentDto) {
        if (Objects.isNull(dependentDto.getIdCustomer())) {
            throw new EntityBadRequestException("O campo cliente não pode ser nulo!");
        }
    }

    private void verifyAmountDependents(DependentDto dependentDto) {
        if (dependentRepository.numberDependentsByCustomerId(dependentDto.getIdCustomer()) == MAX_DEPENDENTS) {
            throw new EntityBadRequestException("O cliente já possui 3 dependentes cadastrados!");
        }
    }

    private void existsCustomerById(Long idCustomer) {
        if (!customerRepository.existsById(idCustomer)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer));
        }
    }

    private void generateRegistrationNumber(DependentDto dependentDto) {
        Random random = new Random();
        dependentDto.setRegistrationNumber(10000L + random.nextInt(90000));
    }

    private void setActiveDependent(DependentDto dependentDto) {
        dependentDto.setActive(true);
    }

    private void validateGender(DependentDto dependentDto) {
        Character gender = dependentDto.getGender();
        if (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
            throw new EntityBadRequestException("Gênero inserido inválido!");
        }
        dependentDto.setGender(Character.toUpperCase(gender));
    }

    private void setLinkedCustomerId(DependentDto dependetDto) {
        dependetDto.setIdCustomer(dependentRepository.findIdCustomerById(dependetDto.getId()));
    }

    private void validateRegistrationNumber(DependentDto dependentDto) {
        if (Objects.isNull(dependentDto.getRegistrationNumber())) {
            dependentDto.setRegistrationNumber(dependentRepository.findRegistrationNumberById(dependentDto.getId()));
            return;
        }

        if (!Objects.equals(dependentDto.getRegistrationNumber(), dependentRepository.findRegistrationNumberById(dependentDto.getId()))) {
            throw new EntityBadRequestException("O número de inscrição não pode ser alterado!");
        }
    }

    private void verifyActive(DependentDto dependentDto) {
        if (Objects.isNull(dependentDto.getActive())) {
            dependentDto.setActive(true);
        }
    }

    public DependentDto create(DependentDto dependentDto) {
        verifyIdCustomerIsNull(dependentDto);
        existsCustomerById(dependentDto.getIdCustomer());
        verifyAmountDependents(dependentDto);
        validateGender(dependentDto);
        generateRegistrationNumber(dependentDto);
        setActiveDependent(dependentDto);
        return dependentMapper.toDto(dependentRepository.save(dependentMapper.toEntity(dependentDto)));
    }

    public DependentDto update(DependentDto dependentDto) {
        existsDependentById(dependentDto.getId());
        setLinkedCustomerId(dependentDto);
        validateRegistrationNumber(dependentDto);
        validateGender(dependentDto);
        verifyActive(dependentDto);
        return dependentMapper.toDto(dependentRepository.save(dependentMapper.toEntity(dependentDto)));
    }

    public void changeActive(Long idDependent) {
        DependentDto dependentDto = findById(idDependent);
        dependentDto.setActive(!dependentDto.getActive());
        dependentRepository.save(dependentMapper.toEntity(dependentDto));
    }

    public void deleteById(Long idDependent) {
        existsDependentById(idDependent);
        dependentRepository.deleteById(idDependent);
    }

}
