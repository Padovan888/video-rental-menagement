package br.com.videolocadorapassatempo.service.impl;

import br.com.videolocadorapassatempo.model.ItemModel;
import br.com.videolocadorapassatempo.model.LocationModel;
import br.com.videolocadorapassatempo.repository.CustomerRepository;
import br.com.videolocadorapassatempo.repository.DependentRepository;
import br.com.videolocadorapassatempo.repository.ItemRepository;
import br.com.videolocadorapassatempo.repository.LocationRepository;
import br.com.videolocadorapassatempo.service.LocationService;
import br.com.videolocadorapassatempo.service.dto.LocationDto;
import br.com.videolocadorapassatempo.service.enums.Entity;
import br.com.videolocadorapassatempo.service.exception.EntityBadRequestException;
import br.com.videolocadorapassatempo.service.exception.EntityNotFoundException;
import br.com.videolocadorapassatempo.service.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final CustomerRepository customerRepository;

    private final DependentRepository dependentRepository;

    private final ItemRepository itemRepository;

    private final LocationMapper locationMapper;

    public List<LocationDto> findAll() {
        return locationMapper.toDto(locationRepository.findAll());
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
        if (Objects.nonNull(idCustomer)) {
            if (!customerRepository.existsById(idCustomer)) {
                throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.CUSTOMER.getName(), idCustomer));
            }
            checkCustomerIsDebt(idCustomer);
        }
    }

    private void existsDependentById(Long idDependent) {
        if (Objects.nonNull(idDependent)) {
            if (!dependentRepository.existsById(idDependent)) {
                throw new EntityNotFoundException(EntityNotFoundException.getMessageError(Entity.DEPENDENT.getName(), idDependent));
            }
            checkDependentIsDebt(idDependent);
        }
    }

    private void checkCustomerIsDebt(Long idCustomer) {
        if (locationRepository.checkCustomerIsDebt(idCustomer)) {
            throw new EntityBadRequestException("Não é possível efetuar a locação pois o cliente se encontra em débito!");
        }
    }

    private void checkDependentIsDebt(Long idDependent) {
        if (locationRepository.checkDependentIsDebt(idDependent)) {
            throw new EntityBadRequestException("Não é possível efetuar a locação pois o dependente se encontra em débito!");
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
        verifyPossibilityRentItem(idItem);
    }

    private void checkHaveCustomer(LocationDto locationDto) {
        if (Objects.isNull(locationDto.getIdCustomer()) && Objects.isNull(locationDto.getIdDependent())) {
            throw new EntityBadRequestException("Uma locação deve ser associada a um cliente ou dependente!");
        }

        if (Objects.nonNull(locationDto.getIdCustomer()) && Objects.nonNull(locationDto.getIdDependent())) {
            throw new EntityBadRequestException("Uma locação deve ser associada a apenas um cliente ou dependente!");
        }

        existsCustomerById(locationDto.getIdCustomer());
        existsDependentById(locationDto.getIdDependent());
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

    private void calculateValueAndExpectedReturnDate(LocationDto locationDto) {
        Optional<ItemModel> itemModel = itemRepository.findById(locationDto.getIdItem());
        locationDto.setValue(itemModel.get().getTitleModel().getClassModel().getValue());
        locationDto.setExpectedReturnDate(LocalDate.now().plusDays(itemModel.get().getTitleModel().getClassModel().getReturnPeriod()));
    }

    public LocationDto create(LocationDto locationDto) {
        checkHaveCustomer(locationDto);
        existsItemById(locationDto.getIdItem());
        setCurrentDate(locationDto);
        checkFieldsMustNull(locationDto);
        calculateValueAndExpectedReturnDate(locationDto);
        return locationMapper.toDto(locationRepository.save(locationMapper.toEntity(locationDto)));
    }

    public LocationDto update(LocationDto locationDto) {
        existsLocationById(locationDto.getId());
        checkHaveCustomer(locationDto);
        existsItemById(locationDto.getIdItem());
        setCurrentDate(locationDto);
        checkFieldsMustNull(locationDto);
        calculateValueAndExpectedReturnDate(locationDto);
        return locationMapper.toDto(locationRepository.save(locationMapper.toEntity(locationDto)));
    }

    public LocationDto customerDebitRecords(Long idCustomer) {
        List<Long> idRecords = locationRepository.customerDebitRecords(idCustomer);
        List<LocationDto> locationRecords = idRecords.stream().map(idRecord -> {
            Optional<LocationModel> locationModel = locationRepository.findById(idRecord);
            return locationModel.map(locationMapper::toDto).orElse(null);
        }).collect(Collectors.toList());
        return (LocationDto) locationRecords;
    }

    public LocationDto dependentDebitRecords(Long idDependent) {
        List<Long> idRecords = locationRepository.dependentDebitRecords(idDependent);
        List<LocationDto> locationRecords = idRecords.stream().map(idRecord -> {
            Optional<LocationModel> locationModel = locationRepository.findById(idRecord);
            return locationModel.map(locationMapper::toDto).orElse(null);
        }).collect(Collectors.toList());
        return (LocationDto) locationRecords;
    }

    public void deleteById(Long idLocation) {
        existsLocationById(idLocation);
        Optional<LocationModel> locationModel = locationRepository.findById(idLocation);
        if (locationModel.get().getPaid()) {
            throw new EntityBadRequestException("Não é possível excluir a locação pois a mesma se encontra paga!");
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
        return locationMapper.toDto(locationRepository.save(locationModel.get()));
    }

}
