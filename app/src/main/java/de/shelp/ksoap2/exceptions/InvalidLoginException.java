package de.shelp.ksoap2.exceptions;

/**
 * @author Roman Busch
 */
public class InvalidLoginException extends Exception {

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
