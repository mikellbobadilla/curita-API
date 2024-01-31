package ar.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<Map<String, String>> greetings() {
        return new ResponseEntity<>(Map.of("message", "Hello, world!"), HttpStatus.OK);
    }
}
