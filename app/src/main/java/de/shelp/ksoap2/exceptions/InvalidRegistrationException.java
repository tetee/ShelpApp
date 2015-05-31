package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidRegistrationException extends Exception {

    public InvalidRegistrationException(String message) {
        super(message);
    }

    public InvalidRegistrationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
