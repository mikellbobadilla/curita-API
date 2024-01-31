package ar.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<Map<String, String>> greetings() {

        try {
            // Aqui va la logica de negocio
            throw new Exception("Error de negocio");
        } catch(Exception exc) {
            // Aqui va la logica de manejo de errores
            return new ResponseEntity<>(Map.of("error", "Hay un problema!" + exc.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Dentro de la salida de datos de Java. Tienes lo que es la salida estandar y lo que es la salid por flujo
     * @return
     */
    @GetMapping("/despacito")
    public ResponseEntity<Map<String, String>> despacito() {
        System.out.println("Despacito!");
        return new ResponseEntity<>(Map.of("message", "Despacito!"), HttpStatus.OK);
    }

    /**
     * Crear un recurso en el servidor
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create() {
        // Aqui se crea el recurso
        // La logica de negocio
        return new ResponseEntity<>(Map.of("message", "Create!"), HttpStatus.CREATED);
    }
}
