package ar.app.controllers;


import ar.app.entities.SectionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.app.services.SectionService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/sections")
@AllArgsConstructor
public class SectionController {
    
    private final SectionService service;

    @PostMapping
    public ResponseEntity<?> create() {
        service.createSection();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SectionEntity>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
