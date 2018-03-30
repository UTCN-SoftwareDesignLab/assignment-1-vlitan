package repository.bank;

import model.User;
import model.validator.Notification;

import java.util.List;

public interface UserRepository {
    boolean add(User user);
    boolean update(User user);
    boolean deleteById(User user);
    Notification<User> findByUsernameAndPassword(String username, String password);
    List<User> findAll();
    Notification<User> findById(int id);
}
