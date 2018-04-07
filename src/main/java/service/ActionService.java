package service;

import model.Action;
import model.ListActivityDTO;
import model.User;
import model.validator.Notification;

import java.sql.Date;
import java.util.List;

public interface ActionService {
    public Notification<Boolean> addAction(Action action);
    public List<Action> getActionsByUserInInterval(ListActivityDTO listActivityDTO);
}
