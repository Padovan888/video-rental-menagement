package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.LocationDto;

import java.util.List;

public interface LocationService {

    List<LocationDto> findAll();

    LocationDto findById(Long idLocation);

    LocationDto create(LocationDto locationDto);

    LocationDto update(LocationDto locationDto);

    void deleteById(Long idLocation);

    LocationDto makeReturn(String serialNumber);

}
