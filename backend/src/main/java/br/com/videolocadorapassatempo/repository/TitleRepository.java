package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.TitleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TitleRepository extends JpaRepository<TitleModel, Long> {

    @Query(value = "select count(tc) > 0 from title_actor tc where id_actor = :idActor", nativeQuery = true)
    boolean existsByIdActor(@Param("idActor") Long idActor);

    boolean existsByDirectorModelId(Long idDirector);

}
