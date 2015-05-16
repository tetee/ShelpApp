package de.fh_muenster.shelpapp.ShelpApp.Exceptions;

/**
 * Created by user on 05.05.15.
 */
public class InvalidTourException extends Exception {

    public InvalidTourException(String message) {
        super(message);
    }

    public InvalidTourException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
