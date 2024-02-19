package ar.app.service;

import ar.app.dto.CreateSectionDTO;
import ar.app.model.section.SectionModel;
import ar.app.repository.section.SectionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class SectionService {

    private final SectionRepository sectionRepository;

    /* CRUD */
    /* Create, Read, Update, Delete */
    /* Create - POST */
    public SectionModel createSection(CreateSectionDTO entity) {
        SectionModel section = SectionModel.builder()
                .id(null)
                .name(entity.name())
                .observation(entity.observation())
                .build();
        
        return sectionRepository.saveAndFlush(section);
    }

    /* READ - GET */
    public List<SectionModel> getAll() {
        return sectionRepository.findAll();
    }

}
