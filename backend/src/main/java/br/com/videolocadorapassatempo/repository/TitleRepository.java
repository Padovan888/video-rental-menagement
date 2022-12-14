package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.TitleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<TitleModel, Long> {

    @Query(value = "select count(tc) > 0 from title_actor tc where id_actor = :idActor", nativeQuery = true)
    boolean existsByIdActor(@Param("idActor") Long idActor);

    boolean existsByDirectorModelId(Long idDirector);

    boolean existsByClassModelId(Long idClass);

    List<TitleModel> findByName(String name);

    List<TitleModel> findByCategory(String category);

    @Query("select t from TitleModel t where exists(from t.actorModel a where a.name = :nameActor)")
    List<TitleModel> findByNameActor(@Param("nameActor") String nameActor);

}
