package fr.cowffee.hexagonal.exception;

import fr.cowffee.core_domain.exception.EssentialOilNotDeletedException;
import fr.cowffee.core_domain.exception.EssentialOilNotFoundException;
import fr.cowffee.core_domain.exception.MedicinalPropertyNotDeletedException;
import fr.cowffee.core_domain.exception.MedicinalPropertyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(MedicinalPropertyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUnprocessable(MedicinalPropertyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(EssentialOilNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUnprocessable(EssentialOilNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MedicinalPropertyNotDeletedException.class)
    public ResponseEntity<Map<String, String>> handleUnprocessable(MedicinalPropertyNotDeletedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(EssentialOilNotDeletedException.class)
    public ResponseEntity<Map<String, String>> handleUnprocessable(EssentialOilNotDeletedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .body(Map.of("message", ex.getMessage()));
    }
}
