package de.shelp.ksoap2.exceptions;

/**
 * @author Roman Busch
 */
public class InvalidRequestException extends Exception {

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
