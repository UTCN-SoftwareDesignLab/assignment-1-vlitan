package service;

import model.User;
import model.validator.Notification;

import java.util.List;

public interface UserService {
    public Notification<Boolean> add(User user);
    public Notification<Boolean> update(User user);
    public Notification<Boolean> deleteById(User user);
    public Notification<User> findById(User user);
    public List<User> findAll();

}
