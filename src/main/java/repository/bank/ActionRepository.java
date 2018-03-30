package repository.bank;

import model.Action;
import model.Transfer;
import model.User;

import java.util.List;

public interface ActionRepository {
    boolean addAction(Action action);
    boolean updateAction(Action action);
    List<Action> findAllActions();
    Action findActionById(int id);
    List<Action> findByUser(User user);
}
