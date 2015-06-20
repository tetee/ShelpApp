package de.shelp.ksoap2.exceptions;

/**
 * @author Theresa Sollert
 */
public class NoSessionException extends Exception {

    public NoSessionException(String message) {
        super(message);
    }

    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
