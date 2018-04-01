package controller;

import model.Role;
import model.User;
import view.LoginView;
import view.UserView;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer{

    public MainController(UserView view){

    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().toString().equals(LoginController.class.toString())){
            System.out.println("[MainController] Success. Current user is: " + ((User)o).getUsername());
            System.out.println("[MainController] listing roles");
            for(Role r : ((User)o).getRoles()){
                System.out.println(r);
            }
        }
        else{
            System.out.println("[MainController] Unknown observable");
        }
    }
}
