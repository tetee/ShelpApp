package de.shelp.ksoap2.exceptions;

/**
 * @author Roman Busch
 */
public class InvalidRegistrationException extends Exception {

    public InvalidRegistrationException(String message) {
        super(message);
    }

    public InvalidRegistrationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
