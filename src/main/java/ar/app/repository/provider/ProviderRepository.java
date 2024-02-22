package ar.app.repository.provider;

import ar.app.model.provider.ProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderModel, Long> {

    boolean existsByName(String name);

    Optional<ProviderModel> findByName(String name);
}
