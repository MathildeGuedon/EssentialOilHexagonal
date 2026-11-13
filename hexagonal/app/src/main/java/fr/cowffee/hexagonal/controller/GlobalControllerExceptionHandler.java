package fr.cowffee.hexagonal.controller;

import fr.cowffee.core_domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(MedicinalPropertyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePropertyNotFound(MedicinalPropertyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(EssentialOilNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOilNotFound(EssentialOilNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MedicinalPropertyNotDeletedException.class)
    public ResponseEntity<Map<String, String>> handlePropertyNotDeleted(MedicinalPropertyNotDeletedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(EssentialOilNotDeletedException.class)
    public ResponseEntity<Map<String, String>> handleOilNotDeleted(EssentialOilNotDeletedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MedicinalPropertyInUseException.class)
    public ResponseEntity<Map<String, String>> handlePropertyInUse(MedicinalPropertyInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getMessage()));
    }
}
