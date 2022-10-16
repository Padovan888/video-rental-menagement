package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query(value = "select c.registrationNumber from CustomerModel c where c.id = :idCustomer")
    Long findRegistrationNumberById(@Param("idCustomer") Long idCustomer);

}
