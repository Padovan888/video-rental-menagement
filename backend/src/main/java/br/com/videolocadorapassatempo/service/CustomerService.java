package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(Long idCustomer);

    CustomerDto create(CustomerDto customerDto);

    CustomerDto update(CustomerDto customerDto);

    void deleteById(Long idCustomer);

}
