package ar.app.controller.provider;

import ar.app.dto.provider.PageProviders;
import ar.app.dto.provider.ProviderRequest;
import ar.app.dto.provider.ProviderResponse;
import ar.app.exception.provider.ProviderException;
import ar.app.exception.provider.ProviderNotFountException;
import ar.app.model.provider.ProviderModel;
import ar.app.service.provider.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@AllArgsConstructor
public class ProviderController {

    private final ProviderService service;

    @GetMapping
    ResponseEntity<PageProviders> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }
 
    @PostMapping
    ResponseEntity<ProviderResponse> create(@RequestBody @Valid ProviderRequest request) throws ProviderException {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProviderModel> get(@PathVariable Long id) throws ProviderNotFountException {
        return new ResponseEntity<>(service.getBy(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProviderRequest request) throws IllegalAccessException, ProviderNotFountException {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ProviderResponse> partialUpdate(@PathVariable Long id, @RequestBody @Valid ProviderRequest request) throws IllegalAccessException, ProviderNotFountException {
        return new ResponseEntity<>(service.partialUpdate(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) throws ProviderNotFountException {
        service.deleteBy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
