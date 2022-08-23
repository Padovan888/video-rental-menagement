package br.com.videolocadorapassatempo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "item_type")
@Getter
@Setter
public class ItemTypeModel implements Serializable {

    @Id
    private Long id;

    @JoinColumn(name = "name", nullable = false)
    private String name;

}
