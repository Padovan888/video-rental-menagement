package br.com.videolocadorapassatempo.model;

import br.com.videolocadorapassatempo.service.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NamedNativeQuery(name = "CustomerModel.findByIdCustomerDto",
        query = "select c.id as id, " +
                "c.registration_number as registrationNumber, " +
                "c.name as name, " +
                "c.birth_date as birthDate, " +
                "c.gender as gender, " +
                "c.active as active, " +
                "c.cpf as cpf, " +
                "c.address as address, " +
                "c.telephone as telephone, " +
                "c.type as type, " +
                "c.id_member as idMember from customer c where c.id = :idCustomer",
        resultSetMapping = "Mapping.CustomerDto")

@NamedNativeQuery(name = "CustomerModel.findDependentsByidMember",
        query = "select c.id as id, " +
                "c.registration_number as registrationNumber, " +
                "c.name as name, " +
                "c.birth_date as birthDate, " +
                "c.gender as gender, " +
                "c.active as active, " +
                "c.cpf as cpf, " +
                "c.address as address, " +
                "c.telephone as telephone, " +
                "c.type as type, " +
                "c.id_member as idMember from customer c where c.id_member = :idMember",
        resultSetMapping = "Mapping.CustomerDto")

@SqlResultSetMapping(name = "Mapping.CustomerDto",
        classes = @ConstructorResult(targetClass = CustomerDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "registrationNumber", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "birthDate", type = LocalDate.class),
                        @ColumnResult(name = "gender", type = Character.class),
                        @ColumnResult(name = "active", type = Boolean.class),
                        @ColumnResult(name = "cpf", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "telephone", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "idMember", type = Long.class)
                }))


@Entity
@Table(name = "customer")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
public class CustomerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_customer")
    @SequenceGenerator(name = "sequence_customer", sequenceName = "sequence_customer", allocationSize = 1)
    private Long id;

    @Column(name = "registration_number", nullable = false)
    private Long registrationNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    private String type;

}
