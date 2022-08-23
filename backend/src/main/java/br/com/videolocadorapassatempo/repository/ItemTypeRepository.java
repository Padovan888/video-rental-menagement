package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.ItemTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemTypeModel, Long> {
}
