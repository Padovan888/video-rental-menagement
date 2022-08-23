package br.com.videolocadorapassatempo.repository;

import br.com.videolocadorapassatempo.model.TitleActorModel;
import br.com.videolocadorapassatempo.model.id.TitleActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TitleActorRepository extends JpaRepository<TitleActorModel, TitleActorId> {

    @Transactional
    @Modifying
    @Query(value = "delete from title_actor ta where ta.id_title = :titleId", nativeQuery = true)
    void deleteAllByTitleId(@Param("titleId") Long titleId);

}
