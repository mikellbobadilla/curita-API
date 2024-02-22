package ar.app.service.section;

import ar.app.dto.section.PageSections;
import ar.app.dto.section.SectionRequest;
import ar.app.dto.section.SectionResponse;
import ar.app.exception.section.SectionException;
import ar.app.exception.section.SectionNotFoundException;
import ar.app.model.section.SectionModel;
import ar.app.repository.section.SectionRepository;
import ar.app.utils.MapperObject;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionService {

    private final SectionRepository repository;
    private final MapperObject mapper;

    public PageSections getAll(int page, int size) throws SectionException {
        --page;
        if (page < 0) {
            throw new SectionException("Page cannot be less than 1");
        }

        Pageable pageable = PageRequest.of(page, size);
        return pageMapper(repository.findAll(pageable));
    }

    public SectionResponse create(SectionRequest request) throws SectionException {
        if (repository.existsByName(request.name()))
            throw new SectionException("Section exists");

        SectionModel section = mapper.mapData(SectionModel.class, request);

        return mapper.mapData(SectionResponse.class, repository.saveAndFlush(section));
    }

    public SectionResponse getBy(Long id) throws SectionNotFoundException {
        return repository.findById(id)
                .map(model -> mapper.mapData(SectionResponse.class, model))
                .orElseThrow(() -> new SectionNotFoundException("Section not found"));
    }

    public void update(Long id, SectionRequest request) throws IllegalAccessException, SectionNotFoundException {
        SectionModel model = repository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException("Section not found"));
        repository.saveAndFlush(mapper.mapData(model, request));
    }

    public SectionResponse partialUpdate(Long id, SectionRequest request) throws IllegalAccessException, SectionNotFoundException {
        SectionModel model = repository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException("Section not found"));
        model = mapper.mapData(model, request);
        repository.saveAndFlush(model);
        return mapper.mapData(SectionResponse.class, model);
    }

    public void deleteBy(Long id) throws SectionNotFoundException {
        if (!repository.existsById(id))
            throw new SectionNotFoundException("Section not found");

        repository.deleteById(id);
    }

    /* Private Methods */
    private PageSections pageMapper(Page<SectionModel> models) {
        List<SectionModel> content = models.getContent();

        return PageSections.builder()
                .content(content.stream().map(model -> mapper.mapData(SectionResponse.class, model)).toList())
                .pageNumber(models.getPageable().getPageNumber() + 1)
                .paseSize(models.getPageable().getPageSize())
                .totalPages(models.getTotalPages())
                .totalElements(models.getTotalElements())
                .build();
    }
}
