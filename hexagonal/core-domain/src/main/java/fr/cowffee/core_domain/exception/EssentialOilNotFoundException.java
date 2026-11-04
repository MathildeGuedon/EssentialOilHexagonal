package fr.cowffee.core_domain.exception;

public class EssentialOilNotFoundException extends RuntimeException {
    public EssentialOilNotFoundException(String message) {
        super(message);
    }
}
