package br.com.videolocadorapassatempo.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ViewCustomerDto implements Serializable {

    private Long id;

    private Long registrationNumber;

    private String name;

    private LocalDate birthDate;

    private Character gender;

    private Boolean active;

    private String type;

}
