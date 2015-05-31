package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class NoSessionException extends Exception {

    public NoSessionException(String message) {
        super(message);
    }

    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
