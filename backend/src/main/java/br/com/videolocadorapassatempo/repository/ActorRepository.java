package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.ActorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActorRepository extends JpaRepository<ActorModel, UUID>{
}
