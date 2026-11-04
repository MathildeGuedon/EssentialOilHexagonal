package fr.cowffee.core_domain.exception;

public class MedicinalPropertyNotFoundException extends RuntimeException {
    public MedicinalPropertyNotFoundException(String message) {
        super(message);
    }
}
