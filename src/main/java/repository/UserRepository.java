package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    boolean dropAllUsers();
    List<User> findAllUsers();
    User findUserById(int id);
}
