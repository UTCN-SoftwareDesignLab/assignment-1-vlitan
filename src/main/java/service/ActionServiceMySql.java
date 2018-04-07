package service;

import model.Action;
import model.ListActivityDTO;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.bank.ActionRepository;

import java.sql.Date;
import java.util.List;

public class ActionServiceMySql implements ActionService{
    ActionRepository actionRepository;

    public ActionServiceMySql(ActionRepository actionRepository){
        this.actionRepository = actionRepository;
    }

    @Override
    public Notification<Boolean> addAction(Action action) {
        Notification<Boolean> addNotification = new Notification<>();

        boolean isValid = true;//TODO implement an action validator

        if (isValid){
            actionRepository.add(action);
            addNotification.setResult(Boolean.TRUE);
        }
        else{
            addNotification.setResult(Boolean.FALSE);
        }
        return addNotification;
    }

    @Override
    public List<Action> getActionsByUserInInterval(ListActivityDTO listActivityDTO) {
        return actionRepository.findByUserInInterval(listActivityDTO);
    }
}
