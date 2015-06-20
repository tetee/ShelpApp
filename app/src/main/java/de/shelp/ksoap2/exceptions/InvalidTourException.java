package de.shelp.ksoap2.exceptions;

/**
 * @author Theresa Sollert
 */
public class InvalidTourException extends Exception {

    public InvalidTourException(String message) {
        super(message);
    }

    public InvalidTourException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
