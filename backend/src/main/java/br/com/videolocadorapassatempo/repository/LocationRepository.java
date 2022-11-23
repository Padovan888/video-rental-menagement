package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.LocationModel;
import br.com.videolocadorapassatempo.service.dto.LocationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationModel, Long> {

    @Query(value = "select count(*) > 0 from LocationModel l where " +
            "l.customerModel.id = :idCustomer and " +
            "l.expectedReturnDate <= CURRENT_DATE and " +
            "l.paid = false")
    Boolean checkCustomerIsDebt(@Param("idCustomer") Long idCustomer);

    @Query("select new br.com.videolocadorapassatempo.service.dto.LocationDto(l.id, " +
            "l.leaseDate, " +
            "l.expectedReturnDate, " +
            "l.actualReturnDate, " +
            "l.value, " +
            "l.penalty, " +
            "l.paid, " +
            "l.customerModel.id, " +
            "l.itemModel.id) from LocationModel l where l.customerModel.id = :idCustomer and " +
            "l.expectedReturnDate <= CURRENT_DATE and " +
            "l.paid = false")
    List<LocationDto> customerDebitRecords(@Param("idCustomer") Long idCustomer);

    @Query(value = "select count(*) > 0 from LocationModel l where " +
            "l.itemModel.id = :idItem and " +
            "l.actualReturnDate is null")
    Boolean verifyNotPossibilityRentItem(@Param("idItem") Long idItem);

    @Query(value = "select l.id from LocationModel l where " +
            "l.itemModel.id = :idItem and " +
            "l.actualReturnDate is null")
    Long returnIdLocation(@Param("idItem") Long idItem);

    @Query("select count(*) > 0 from LocationModel l where l.id = :idLocation and " +
            "l.paid = true")
    Boolean isLocationPaid(@Param("idLocation") Long idLocation);

    @Query("select count(*) > 0 from LocationModel l where l.customerModel.id = :idCustomer")
    Boolean customerHaveLocation(@Param("idCustomer") Long idCustomer);

}
