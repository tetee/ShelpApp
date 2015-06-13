package de.shelp.ksoap2.exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidUsersException extends Exception {

    public InvalidUsersException(String message) {
        super(message);
    }

    public InvalidUsersException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
