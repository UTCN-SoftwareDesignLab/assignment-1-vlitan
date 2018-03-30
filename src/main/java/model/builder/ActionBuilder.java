package model.builder;

import model.Account;
import model.Action;
import model.Transfer;

import java.sql.Date;

public class ActionBuilder {
    private Action action;

    public ActionBuilder(){
        action = new Action();
    }

    public ActionBuilder setId(int id){
        action.setId(id);
        return this;
    }
    public ActionBuilder setDescription(String description){
        action.setDescription(description);
        return this;
    }
    public ActionBuilder setDate(Date date){
        action.setDate(date);
        return this;
    }
    public ActionBuilder setUserId(int id){
        action.setUserId(id);
        return this;
    }
    public Action build(){
        return action;
    }
}
