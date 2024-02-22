package ar.app.repository.section;

import ar.app.model.section.SectionModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@ActiveProfiles("test")
class SectionRepositoryTest {

    @Autowired
    private SectionRepository repository;
    @Autowired
    private TestEntityManager manager;

    @Test
    void contextLoads() {
        assertNotNull(repository);
        assertNotNull(manager);
    }

    @Test
    void verifyPersistSectionModel() {
        SectionModel model = sectionBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
    }

    @Test
    void verifyPersistAndFindSectionById() {
        SectionModel model = sectionBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        SectionModel section = manager.find(SectionModel.class, model.getId());
        assertNotNull(section);
        assertEquals(model.getId(), section.getId());
    }

    @Test
    void verifyPersisAndFindSectionByName() {
        SectionModel model = repository.saveAndFlush(sectionBuilder());
        assertNotNull(model);
        assertDoesNotThrow(
                () -> repository.findByName(model.getName()).orElseThrow()
        );
    }

    @Test
    void verifyPersistAndCheckIfExistsSection() {
        SectionModel model = repository.saveAndFlush(sectionBuilder());
        assertNotNull(model);
        assertTrue(repository.existsByName(model.getName()));
    }

    @Test
    void verifyPersistAndUpdateSection() {
        SectionModel model = sectionBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        assertEquals("soap", model.getName());
        model.setName("cream");
        manager.persist(model);
        assertEquals("cream", model.getName());
    }

    @Test
    void verifyPersistAndDeleteSection() {
        SectionModel model = sectionBuilder();
        assertNull(model.getId());
        manager.persist(model);
        assertNotNull(model.getId());
        manager.remove(model);
        SectionModel section = manager.find(SectionModel.class, model.getId());
        assertNull(section);
    }

    /* ------------------------------------------------------------------ */
    private SectionModel sectionBuilder() {
        return SectionModel.builder()
                .name("soap")
                .observation("Text to observation")
                .build();
    }
}