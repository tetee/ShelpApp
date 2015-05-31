package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidLoginException extends Exception {

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
