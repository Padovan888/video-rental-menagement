package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassModel, Long> {
}
