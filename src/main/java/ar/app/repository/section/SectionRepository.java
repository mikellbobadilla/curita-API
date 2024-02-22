package ar.app.repository.section;

import ar.app.model.section.SectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<SectionModel, Long> {

    boolean existsByName(String name);

    Optional<SectionModel> findByName(String name);
}
