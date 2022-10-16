package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.DependentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DependentRepository extends JpaRepository<DependentModel, Long> {

    @Query(value = "select d.customerModel.id from DependentModel d where d.id = :idDependent")
    Long findIdCustomerById(@Param("idDependent") Long idDependent);

    @Query(value = "select d.registrationNumber from DependentModel d where d.id = :idDependent")
    Long findRegistrationNumberById(@Param("idDependent") Long idDependent);

    @Query(value = "select count(*) from DependentModel d where d.customerModel.id = :idCustomer")
    Integer numberDependentsByCustomerId(@Param("idCustomer") Long idCustomer);

}
