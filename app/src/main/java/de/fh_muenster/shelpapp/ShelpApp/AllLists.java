package de.fh_muenster.shelpapp.ShelpApp;

import java.util.List;

/**
 * Created by user on 21.05.15.
 */
public class AllLists {

    private List<String> cap;

    public AllLists(List<String> cap){
        this.cap = cap;
    }

    public List<String> getCapacity(){
        return this.cap;
    }
}
