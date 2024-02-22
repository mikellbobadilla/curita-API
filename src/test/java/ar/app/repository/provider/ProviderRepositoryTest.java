package ar.app.repository.provider;

import ar.app.model.provider.ProviderModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@ActiveProfiles("test")
class ProviderRepositoryTest {

    @Autowired
    private ProviderRepository repository;
    @Autowired
    private TestEntityManager manager;

    @Test
    void contextLoads() {
        assertNotNull(repository);
        assertNotNull(manager);
    }

    @Test
    void verifyPersistProviderModel() {
        ProviderModel model = providerBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
    }

    @Test
    void verifyPersistAndFinProviderById() {
        ProviderModel model = providerBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        ProviderModel provider = manager.find(ProviderModel.class, model.getId());
        assertNotNull(provider);
        assertEquals(model.getName(), provider.getName());
    }

    @Test
    void verifyPersistAndFindProviderByName() {
        ProviderModel model = repository.saveAndFlush(providerBuilder());
        assertNotNull(model);
        assertDoesNotThrow(
                () -> repository.findByName(model.getName()).orElseThrow()
        );
    }

    @Test
    void verifyPersistAndCheckIfExistsProvider() {
        ProviderModel model = repository.saveAndFlush(providerBuilder());
        assertNotNull(model);
        assertTrue(repository.existsByName(model.getName()));
    }

    @Test
    void verifyPersistAndUpdateProvider() {
        ProviderModel model = providerBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        assertEquals("Colgate", model.getName());
        model.setName("Avon");
        manager.persist(model);
        assertEquals("Avon", model.getName());
    }

    @Test
    void verifyPersistAndDeleteProvider() {
        ProviderModel model = providerBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        manager.remove(model);
        ProviderModel provider = manager.find(ProviderModel.class, model.getId());
        assertNull(provider);
    }

    /* ------------------------------------------------------------------ */
    private ProviderModel providerBuilder() {
        return ProviderModel.builder()
                .name("Colgate")
                .address("Av. Street 210")
                .email("colagate@gmail.com")
                .contactProvider("Alexis")
                .phone("+5491165185398")
                .cuil("20-12345678-1")
                .startOperations(new Date())
                .observation("Text to observation")
                .build();
    }


}