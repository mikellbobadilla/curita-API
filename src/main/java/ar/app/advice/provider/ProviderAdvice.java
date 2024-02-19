package ar.app.advice.provider;

import ar.app.advice.ErrorResponse;
import ar.app.exceptions.provider.ProviderException;
import ar.app.exceptions.provider.ProviderNotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProviderAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> argumentException(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new HashMap<>(10);
        exc.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ProviderException.class)
    ResponseEntity<ErrorResponse> providerException(ProviderException exc) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exc.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProviderNotFountException.class)
    ResponseEntity<ErrorResponse> providerNotFoundException(ProviderNotFountException exc) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exc.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
