package repository.bank;

import model.Action;
import model.Transfer;
import model.User;

import java.util.List;

public interface ActionRepository {
    boolean add(Action action);
    boolean update(Action action);
    List<Action> findAll();
    Action findById(int id);
    List<Action> findByUser(User user);
}
