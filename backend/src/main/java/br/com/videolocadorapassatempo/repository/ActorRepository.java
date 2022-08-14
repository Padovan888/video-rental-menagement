package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.ActorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorModel, Long>{
}
