package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    ItemModel findBySerialNumber(String serialNumber);

}
