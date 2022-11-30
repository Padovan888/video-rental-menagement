package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.LocationDto;
import br.com.videolocadorapassatempo.service.dto.ViewLocationDto;

import java.util.List;

public interface LocationService {

    List<ViewLocationDto> findAll();

    LocationDto findById(Long idLocation);

    LocationDto create(LocationDto locationDto);

    LocationDto update(LocationDto locationDto);

    void deleteById(Long idLocation);

    LocationDto makeReturn(String serialNumber);

}
