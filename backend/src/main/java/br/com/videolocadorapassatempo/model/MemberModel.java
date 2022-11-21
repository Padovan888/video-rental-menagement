package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class MemberModel extends CustomerModel {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

}
