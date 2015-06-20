package de.shelp.ksoap2;

import java.util.List;

/**
 * Rückgabetyp für allgemeine Objecte im Response
 * beinhaltet eine Liste der vom Server zurÃ¼ck gegebenen Objekte und einer Fehlermeldung falls aufgetreten
 *
 * @param <T> - Typ des Objektes vom Server
 *
 */
public class ObjectResponse<T> {

    private List<T> list;
    private String message;

    public ObjectResponse(List<T> list, String message) {
        this.list = list;
        this.message = message;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
