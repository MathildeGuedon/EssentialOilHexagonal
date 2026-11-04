package fr.cowffee.core_domain.exception;

public class MedicinalPropertyNotDeletedException extends RuntimeException {
    public MedicinalPropertyNotDeletedException(String message) {
        super(message);
    }
}
