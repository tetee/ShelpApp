package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidRequestException extends Exception {

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
