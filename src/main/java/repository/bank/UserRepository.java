package repository.bank;

import model.User;

import java.util.List;

public interface UserRepository {
    boolean add(User user);
    boolean update(User user);
    boolean deleteById(User user);
    List<User> findAll();
    User findById(int id);
}
