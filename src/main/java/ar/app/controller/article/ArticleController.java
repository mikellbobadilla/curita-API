package ar.app.controller.article;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.app.dto.article.ArticleRequest;
import ar.app.dto.article.ArticleResponse;
import ar.app.dto.article.PageArticles;
import ar.app.exception.article.ArticleException;
import ar.app.exception.article.ArticleNotFoundException;
import ar.app.exception.provider.ProviderNotFountException;
import ar.app.exception.section.SectionNotFoundException;
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
    ResponseEntity<PageArticles> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) throws ArticleException {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @PostMapping
    ResponseEntity<ArticleResponse> create(@RequestBody @Valid ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, ArticleException {

        return ResponseEntity.created(null).body(service.create(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<ArticleResponse> getById(@PathVariable Long id) throws ArticleNotFoundException {
        return ResponseEntity.ok(service.getBy(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ArticleRequest request)
            throws ProviderNotFountException, SectionNotFoundException, IllegalAccessException, ArticleException {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<ArticleResponse> partialUpdate(@PathVariable Long id, @RequestBody @Valid ArticleRequest request)
            throws IllegalAccessException, ArticleException {
        return ResponseEntity.ok(service.partialUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) throws ArticleNotFoundException {
        service.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
