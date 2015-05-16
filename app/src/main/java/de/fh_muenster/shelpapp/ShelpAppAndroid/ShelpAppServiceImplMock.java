package de.fh_muenster.shelpapp.ShelpAppAndroid;

import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.User;

/**
 * Created by user on 05.05.15.
 */
public class ShelpAppServiceImplMock implements ShelpAppService {

    private User user;

    public ShelpAppServiceImplMock() {}

    @Override
    public User login(String username, String hash) throws InvalidLoginException {
        //Testuser anlegen
        user = new User("busch.roman20@gmail.com", "cc03e747a6afbbcbf8be7668acfebee5");
        return this.user;
    }

    @Override
    public void logout() { this.user = null; }

}
