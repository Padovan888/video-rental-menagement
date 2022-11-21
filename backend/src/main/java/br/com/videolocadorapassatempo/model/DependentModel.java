package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DependentModel extends CustomerModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private MemberModel memberModel;

}
