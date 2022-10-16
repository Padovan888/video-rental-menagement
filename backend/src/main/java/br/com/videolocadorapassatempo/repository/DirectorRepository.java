package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.DirectorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<DirectorModel, Long> {

}
