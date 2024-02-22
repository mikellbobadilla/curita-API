package ar.app.advice.article;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ar.app.advice.ErrorResponse;
import ar.app.exception.article.ArticleException;
import ar.app.exception.article.ArticleNotFoundException;

@RestControllerAdvice
public class ArticleAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> argumentException(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new HashMap<>(10);
        exc.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ArticleException.class)
    ResponseEntity<ErrorResponse> articleException(ArticleException exc) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exc.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    ResponseEntity<ErrorResponse> articleNotFoundException(ArticleNotFoundException exc) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exc.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
