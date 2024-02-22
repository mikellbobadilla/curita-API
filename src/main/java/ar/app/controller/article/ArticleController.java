package ar.app.controller.article;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.app.dto.article.ArticleRequest;
import ar.app.exception.provider.ProviderNotFountException;
import ar.app.exception.section.SectionNotFoundException;
import ar.app.model.article.ArticleModel;
import ar.app.service.article.ArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping
    ResponseEntity<Page<ArticleModel>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @PostMapping
    ResponseEntity<ArticleModel> create(@RequestBody @Valid ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException {

        return ResponseEntity.created(null).body(service.create(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<ArticleModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBy(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, IllegalAccessException {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<ArticleModel> partialUpdate(@PathVariable Long id, @RequestBody @Valid ArticleRequest request)
            throws IllegalAccessException {
        return ResponseEntity.ok(service.partialUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
