package ar.app.controllers.provider;

import ar.app.dto.provider.ProviderRequest;
import ar.app.dto.provider.ProviderResponse;
import ar.app.entities.ProviderEntity;
import ar.app.services.provider.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/providers")
@AllArgsConstructor
public class ProviderController {

    private final ProviderService service;

    @GetMapping
    ResponseEntity<Page<ProviderEntity>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }
 
    @PostMapping
    ResponseEntity<ProviderResponse> create(@RequestBody @Valid ProviderRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProviderEntity> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.getBy(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProviderRequest request) throws IllegalAccessException {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* Todo: Need to implement */
    @PatchMapping("/{id}")
    ResponseEntity<ProviderResponse> partialUpdate(@PathVariable Long id, @RequestBody @Valid ProviderRequest request) throws IllegalAccessException {
        return new ResponseEntity<>(service.partialUpdate(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
