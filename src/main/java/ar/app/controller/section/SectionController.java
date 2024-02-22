package ar.app.controller.section;

import ar.app.dto.section.PageSections;
import ar.app.dto.section.SectionRequest;
import ar.app.dto.section.SectionResponse;
import ar.app.exception.section.SectionException;
import ar.app.exception.section.SectionNotFoundException;
import ar.app.service.section.SectionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sections")
@AllArgsConstructor
public class SectionController {

    private final SectionService service;

    @GetMapping
    ResponseEntity<PageSections> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws SectionException {
        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<SectionResponse> create(@RequestBody @Valid SectionRequest request) throws SectionException {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<SectionResponse> get(@PathVariable Long id) throws SectionNotFoundException {
        return new ResponseEntity<>(service.getBy(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid SectionRequest request) throws IllegalAccessException, SectionNotFoundException {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    ResponseEntity<SectionResponse> partialUpdate(@PathVariable Long id, @RequestBody @Valid SectionRequest request) throws IllegalAccessException, SectionNotFoundException {
        return new ResponseEntity<>(service.partialUpdate(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) throws SectionNotFoundException {
        service.deleteBy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
