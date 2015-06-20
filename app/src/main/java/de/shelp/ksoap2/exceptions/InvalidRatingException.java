package de.shelp.ksoap2.exceptions;

/**
 * @author Roman Busch
 */
public class InvalidRatingException extends Exception {

    public InvalidRatingException(String message) {
        super(message);
    }

    public InvalidRatingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
