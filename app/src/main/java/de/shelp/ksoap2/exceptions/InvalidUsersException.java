package de.shelp.ksoap2.exceptions;

/**
 * @author Theresa Sollert
 */
public class InvalidUsersException extends Exception {

    public InvalidUsersException(String message) {
        super(message);
    }

    public InvalidUsersException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
