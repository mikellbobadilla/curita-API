package ar.app.services;

import ar.app.dto.CreateSectionDTO;
import ar.app.entities.SectionEntity;
import ar.app.repositories.SectionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
@Data
public class SectionService {

    private final SectionRepository sectionRepository;

    /* CRUD */
    /* Create, Read, Update, Delete */
    /* Create - POST */
    public SectionEntity createSection(CreateSectionDTO entity) {
        SectionEntity section = SectionEntity.builder()
                .id(null)
                .name(entity.name())
                .observation(entity.observation())
                .build();
        
        return sectionRepository.saveAndFlush(section);
    }

    /* READ - GET */
    public List<SectionEntity> getAll() {
        return sectionRepository.findAll();
    }

}
