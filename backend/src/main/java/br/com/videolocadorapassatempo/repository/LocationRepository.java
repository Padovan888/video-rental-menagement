package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationModel, Long> {

    @Query(value = "select count(*) > 0 from LocationModel l where " +
            "l.customerModel.id = :idCustomer and " +
            "l.expectedReturnDate < CURRENT_DATE and " +
            "l.paid = false")
    Boolean checkCustomerIsDebt(@Param("idCustomer") Long idCustomer);

    @Query(value = "select count(*) > 0 from LocationModel l where " +
            "l.dependentModel.id = :idDependent and " +
            "l.expectedReturnDate < CURRENT_DATE and " +
            "l.paid = false")
    Boolean checkDependentIsDebt(@Param("idDependent") Long idDependent);

    @Query(value = "select l.id from LocationModel l where " +
            "l.customerModel.id = :idCustomer and " +
            "l.expectedReturnDate < CURRENT_DATE and " +
            "l.paid = false")
    List<Long> customerDebitRecords(@Param("idCustomer") Long idCustomer);

    @Query(value = "select l.id from LocationModel l where " +
            "l.dependentModel.id = :idDependent and " +
            "l.expectedReturnDate < CURRENT_DATE and " +
            "l.paid = false")
    List<Long> dependentDebitRecords(@Param("idDependent") Long idDependent);

    @Query(value = "select count(*) > 0 from LocationModel l where " +
            "l.itemModel.id = :idItem and " +
            "l.actualReturnDate is null")
    Boolean verifyNotPossibilityRentItem(@Param("idItem") Long idItem);

    @Query(value = "select l.id from LocationModel l where " +
            "l.itemModel.id = :idItem and " +
            "l.actualReturnDate is null")
    Long returnIdLocation(@Param("idItem") Long idItem);

}
