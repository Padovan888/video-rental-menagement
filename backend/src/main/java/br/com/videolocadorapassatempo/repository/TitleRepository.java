package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.TitleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<TitleModel, Long> {
}
