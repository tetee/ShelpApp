package de.fh_muenster.shelpapp.ShelpApp;

import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;

/**
 * Created by user on 05.05.15.
 */
public interface ShelpAppService {

    public User login(String userName, String password) throws InvalidLoginException;

    public void logout() throws NoSessionException;

}
