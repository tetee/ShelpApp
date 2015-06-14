package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidRatingException extends Exception {

    public InvalidRatingException(String message) {
        super(message);
    }

    public InvalidRatingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
