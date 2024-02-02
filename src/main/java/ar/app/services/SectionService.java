package ar.app.services;

import ar.app.entities.SectionEntity;
import ar.app.repositories.SectionRepository;
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
    public void createSection() {
        SectionEntity section = SectionEntity.builder()
                .id(null)
                .name("Pasta Dental")
                .observation("Esto es una puta mierda")
                .build();
        
        sectionRepository.save(section);
        sectionRepository.flush();
    }

    /* READ - GET */
    public List<SectionEntity> getAll() {
        return sectionRepository.findAll();
    }

}
