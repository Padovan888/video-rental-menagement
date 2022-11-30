package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ItemModel;
import br.com.videolocadorapassatempo.model.LocationModel;
import br.com.videolocadorapassatempo.repository.CustomerRepository;
import br.com.videolocadorapassatempo.repository.ItemRepository;
import br.com.videolocadorapassatempo.repository.LocationRepository;
import br.com.videolocadorapassatempo.service.LocationService;
import br.com.videolocadorapassatempo.service.dto.LocationDto;
import br.com.videolocadorapassatempo.service.dto.ViewLocationDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.LocationMapper;
import br.com.videolocadorapassatempo.service.mapper.ViewLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final LocationMapper locationMapper;

    private final ViewLocationMapper viewLocationMapper;

    public List<ViewLocationDto> findAll() {
        return viewLocationMapper.toDto(locationRepository.findAll());
    }

    public LocationDto findById(Long idLocation) {
        return locationMapper.toDto(locationRepository.findById(idLocation)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.LOCATION.getName(), idLocation))));
    }

    private void existsLocationById(Long idLocation) {
        if (!locationRepository.existsById(idLocation)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.LOCATION.getName(), idLocation));
        }
    }

    private void existsCustomerById(Long idCustomer) {
        if (!customerRepository.existsById(idCustomer)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer));
        }
    }

    private void checkCustomerIsDebt(LocationDto locationDto) {
        if (locationRepository.checkCustomerIsDebt(locationDto.getIdCustomer())) {
            locationDto.setDebitRecords(locationRepository.customerDebitRecords(locationDto.getIdCustomer()));
        }
    }

    private void verifyPossibilityRentItem(Long idItem) {
        if (locationRepository.verifyNotPossibilityRentItem(idItem)) {
            throw new EntityBadRequestException("Não é possível alugar o item pois o mesmo já se encontra alugado");
        }
    }

    private void existsItemById(Long idItem) {
        if (!itemRepository.existsById(idItem)) {
            throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.ITEM.getName(), idItem));
        }
    }

    private void setCurrentDate(LocationDto locationDto) {
        locationDto.setLeaseDate(LocalDate.now());
    }

    private void checkFieldsMustNull(LocationDto locationDto) {
        if (Objects.nonNull(locationDto.getActualReturnDate())) {
            throw new EntityBadRequestException("O campo data de devolução efetiva deve ser nulo!");
        }

        if (Objects.nonNull(locationDto.getPenalty())) {
            throw new EntityBadRequestException("O campo multa cobrada deve ser nulo!");
        }
    }

    public LocationDto create(LocationDto locationDto) {
        existsCustomerById(locationDto.getIdCustomer());
        existsItemById(locationDto.getIdItem());
        verifyPossibilityRentItem(locationDto.getIdItem());
        setCurrentDate(locationDto);
        checkFieldsMustNull(locationDto);
        checkCustomerIsDebt(locationDto);
        if (locationDto.getDebitRecords().isEmpty()) {
            return locationMapper.toDto(locationRepository.save(locationMapper.toEntity(locationDto)));
        }
        return locationDto;
    }

    public LocationDto update(LocationDto locationDto) {
        existsLocationById(locationDto.getId());
        existsCustomerById(locationDto.getIdCustomer());
        existsItemById(locationDto.getIdItem());
        checkFieldsMustNull(locationDto);
        return locationMapper.toDto(locationRepository.save(locationMapper.toEntity(locationDto)));
    }

    public void deleteById(Long idLocation) {
        existsLocationById(idLocation);
        if (!locationRepository.isLocationPaid(idLocation)) {
            throw new EntityBadRequestException("Não é possível excluir a locação pois a mesma não se encontra paga!");
        }
        locationRepository.deleteById(idLocation);
    }

    private void verifyItsLeased(Long idLocation, String serialNumber) {
        if (Objects.isNull(idLocation)) {
            throw new EntityBadRequestException("O item de serial " + serialNumber + " não se encontra em locação no momento!");
        }
    }

    private void verifyExistsItem(ItemModel itemModel) {
        if (Objects.isNull(itemModel)) {
            throw new EntityBadRequestException("O número de serial inserido não corresponde a nenhum item!");
        }
    }

    private void verifyArrears(LocationModel locationModel) {
        if (LocalDate.now().isAfter(locationModel.getExpectedReturnDate())) {
            locationModel.setPenalty(10.00);
        }
    }

    public LocationDto makeReturn(String serialNumber) {
        ItemModel itemModel = itemRepository.findBySerialNumber(serialNumber);
        verifyExistsItem(itemModel);
        Long idLocation = locationRepository.returnIdLocation(itemModel.getId());
        verifyItsLeased(idLocation, serialNumber);
        Optional<LocationModel> locationModel = locationRepository.findById(idLocation);
        verifyArrears(locationModel.get());
        locationModel.get().setActualReturnDate(LocalDate.now());
        locationModel.get().setPaid(true);
        return locationMapper.toDto(locationRepository.save(locationModel.get()));
    }

}
