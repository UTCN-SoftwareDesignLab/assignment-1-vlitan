package controller;

import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.Right;
import model.Role;
import model.User;
import repository.security.RightsRolesRepositoryMySQL;
import service.RoleRightsService;
import service.RoleRightsServiceImpl;
import view.LoginView;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import static database.Constants.Rights.CREATE_ACCOUNT;

public class MainController implements Observer{
    private User currentUser;
    private UserView userView;
    private RoleRightsService roleRightsService;
    public MainController(UserView userView){
        this.userView = userView;
        userView.setVisible(false);
        userView.setBtnInsertAccountActionListener(new InsertAccountActionListener());
        userView.setBtnInsertClientListener(new InsertClientListener());
        userView.setBtnMakeTransferActionListener(new MakeTransferActionListener());
        userView.setBtnPayBillActionListener(new PayBillActionListener());
        roleRightsService = new RoleRightsServiceImpl(new RightsRolesRepositoryMySQL
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)).getConnection());
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().toString().equals(LoginController.class.toString())){
            currentUser = (User)o;
            System.out.println("[MainController] Success. Current user is: " + currentUser.getUsername());
            userView.setLoggedUsername(currentUser.getUsername());
            System.out.println(roleRightsService.hasRight(currentUser, CREATE_ACCOUNT));
        }
        else{
            System.out.println("[MainController] Unknown observable");
        }
    }

    private class InsertAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class InsertClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class MakeTransferActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class PayBillActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

}
