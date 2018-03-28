package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUserById(User user);
    List<User> findAllUsers();
    User findUserById(int id);
}
