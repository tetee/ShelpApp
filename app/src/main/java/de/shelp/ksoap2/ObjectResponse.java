package de.shelp.ksoap2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jos-Laptop on 16.06.2015.
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
