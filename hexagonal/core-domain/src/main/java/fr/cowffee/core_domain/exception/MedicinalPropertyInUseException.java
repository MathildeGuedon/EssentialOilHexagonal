package fr.cowffee.core_domain.exception;

public class MedicinalPropertyInUseException extends RuntimeException {
    public MedicinalPropertyInUseException(String message) {
        super(message);
    }
}
