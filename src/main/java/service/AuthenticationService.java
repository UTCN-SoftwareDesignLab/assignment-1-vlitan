package service;

import model.Role;
import model.User;
import model.validator.Notification;

public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);

}