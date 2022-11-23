package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.CustomerModel;
import br.com.videolocadorapassatempo.service.dto.CustomerDto;
import br.com.videolocadorapassatempo.service.dto.ViewCustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query(value = "select c.registrationNumber from CustomerModel c where c.id = :idCustomer")
    Long findRegistrationNumberById(@Param("idCustomer") Long idCustomer);

    @Query(nativeQuery = true)
    CustomerDto findByIdCustomerDto(Long idCustomer);

    @Query(nativeQuery = true)
    List<CustomerDto> findDependentsByidMember(Long idMember);

    @Query(value = "select count(*) > 0 from customer c where c.id = :idMember and c.type = 'MemberModel'", nativeQuery = true)
    Boolean existsMemberById(@Param("idMember") Long idMember);

    @Query(value = "select count(*) > 0 from customer c where c.id = :idDependent and c.type = 'DependentModel'", nativeQuery = true)
    Boolean existsDependentById(@Param("idDependent") Long idDependent);

    @Query(value = "select count(*) >= 3 from customer c where c.id_member = :idMember and c.type = 'DependentModel'", nativeQuery = true)
    Boolean checkPossibilityCreateDependent(@Param("idMember") Long idMember);

    @Modifying
    @Transactional
    @Query(value = "update customer set active = :active where id = :idMember or id_member = :idMember", nativeQuery = true)
    void changeActiveMember(@Param("idMember") Long idMember, @Param("active") Boolean active);

    @Modifying
    @Transactional
    @Query(value = "update customer set active = :active where id = :idDependent", nativeQuery = true)
    void changeActiveDependent(@Param("idDependent") Long idDependent, @Param("active") Boolean active);

    @Modifying
    @Transactional
    @Query(value = "delete from customer where id_member = :idMember", nativeQuery = true)
    void deleteDependentsByIdMember(@Param("idMember") Long idMember);

    @Query("select new br.com.videolocadorapassatempo.service.dto.ViewCustomerDto(c.id, " +
            "c.registrationNumber, " +
            "c.name, " +
            "c.birthDate, " +
            "c.gender, " +
            "c.active, " +
            "c.type) from CustomerModel c where c.active = true")
    List<ViewCustomerDto> findAllActive();
    
    @Query(
        value = "select * from customer where customer.type = :type",
        nativeQuery = true)
    List<CustomerModel> findAllByType(@Param("type") String type);

}
