package controller;

import controller.OperationsController.AccountOperationsController;
import controller.OperationsController.ClientOperationsController;
import controller.OperationsController.MiscOperationsController;
import controller.OperationsController.UserOperationsController;
import database.Constants;
import model.*;
import service.RoleRightsService;
import view.AdminView;
import view.UserView;

import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer{
    private User currentUser;
    private UserView userView;
    private AdminView adminView;
    private final RoleRightsService roleRightsService;
    private final AccountOperationsController accountOperationsController;
    private final ClientOperationsController clientOperationsController;
    private final MiscOperationsController miscOperationsController;
    private final UserOperationsController userOperationsController;

    public MainController(RoleRightsService roleRightsService, UserView userView, AdminView adminView, AccountOperationsController accountOperationsController, ClientOperationsController clientOperationsController, MiscOperationsController miscOperationsController, UserOperationsController userOperationsController){
        this.userView = userView;
        this.adminView = adminView;
        this.roleRightsService = roleRightsService;
        this.accountOperationsController = accountOperationsController;
        this.clientOperationsController = clientOperationsController;
        this.miscOperationsController = miscOperationsController;
        this.userOperationsController = userOperationsController;
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().toString().equals(LoginController.class.toString())){
            currentUser = (User)o;

            System.out.println("[MainController] Success. Current user is: " + currentUser.getUsername());
            accountOperationsController.setCurrentUser(currentUser);
            miscOperationsController.setCurrentUser(currentUser);
            clientOperationsController.setCurrentUser(currentUser);
            userOperationsController.setCurrentUser(currentUser);
            if (currentUser.getRoles().contains(roleRightsService.getRoleByTitle(Constants.Roles.ADMINISTRATOR))){
                adminView.setVisible(true);
                adminView.setTitle("logged user: " + currentUser.getUsername());
            }
            else{
                userView.setVisible(true);
                userView.setTitle("logged user: " + currentUser.getUsername());
            }
        }
        else{
            System.out.println("[MainController] Unknown observable");
        }
    }








}
