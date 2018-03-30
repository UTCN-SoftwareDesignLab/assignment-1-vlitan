package service;

import model.Action;
import model.User;
import model.validator.Notification;

import java.sql.Date;
import java.util.List;

public interface ActionService {
    public Notification<Boolean> addAction(Action action);
    public List<Action> getActionsByUserInInterval(User user, Date start, Date end);
}
