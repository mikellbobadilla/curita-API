package ar.app.controller;


import ar.app.dto.CreateSectionDTO;
import ar.app.model.section.SectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.app.service.SectionService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/sections")
@AllArgsConstructor
public class SectionController {
    
    private final SectionService service;

    @PostMapping
    public ResponseEntity<SectionModel> create(@RequestBody CreateSectionDTO dto) {
        
        return new ResponseEntity<>(service.createSection(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SectionModel>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /* Actualizar un recurso */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}
