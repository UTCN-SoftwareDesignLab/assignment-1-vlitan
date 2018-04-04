package controller;

import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.*;
import model.validator.Notification;
import repository.security.RightsRolesRepositoryMySQL;
import service.RoleRightsService;
import service.RoleRightsServiceImpl;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static database.Constants.Rights.*;
import static database.Constants.Roles.ADMINISTRATOR;

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
        userView.setBtnFindAllAccountsActionListener(new FindAllAccountsActionListener());
        userView.setBtnFindAllClientsActionListener(new FindAllClientsActionListener());
        userView.setBtnUpdateAccountActionListener(new UpdateAccountActionListener());
        userView.setBtnUpdateClientActionListener(new UpdateClientActionListener());
        userView.setBtnUpdateUserActionListener(new UpdateUserActionListener());
        userView.setBtnInsertUserActionListener(new InsertUserActionListener());
        userView.setBtnDeleteUserActionListener(new DeleteUserActionListener());
        userView.setBtnListUsersActionListener(new ListUserActionListener());
        userView.setBtnDeleteAccountActionListener(new DeleteAccountActionListener());
        userView.setBtnViewActivityActionListener(new ViewActivityActionListener());
        roleRightsService = new RoleRightsServiceImpl(new RightsRolesRepositoryMySQL
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)).getConnection()));
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().toString().equals(LoginController.class.toString())){
            currentUser = (User)o;
            System.out.println("[MainController] Success. Current user is: " + currentUser.getUsername());
            userView.setLoggedUsername(currentUser.getUsername());
            userView.setVisible(true);
        }
        else{
            System.out.println("[MainController] Unknown observable");
        }
    }

    private class FindAllAccountsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userView.setData("all acounts");
        }
    }

    private class UpdateAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, UPDATE_ACCOUNT)){
                Notification<Account> accountNotification = (new AccountInputDialog(new JPanel())).getAccount();
                if (!accountNotification.hasErrors()){
                    System.out.println("update account: " + accountNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + UPDATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(roleRightsService.hasRight(currentUser, DELETE_ACCOUNT)){
                Notification<Account> accountNotification = (new AccountInputDialog(new JPanel())).getAccount();
                if (!accountNotification.hasErrors()){
                    System.out.println("delete account: " + accountNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + DELETE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class InsertAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, CREATE_ACCOUNT)){
                Notification<Account> accountNotification = (new AccountInputDialog(new JPanel())).getAccount();
                if (!accountNotification.hasErrors()){
                    System.out.println("insert account " + accountNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + CREATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class MakeTransferActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, MAKE_TRANSFER)){

            }
        }
    }

    private class PayBillActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, PAY_BILL)){

            }
        }
    }

    private class InsertClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, CREATE_CLIENT)){
                Notification<Client> clientNotification = (new ClientInputDialog(new JPanel())).getClient();
                if (!clientNotification.hasErrors()){
                    System.out.println("insert client: " + clientNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, clientNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + CREATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UpdateClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, UPDATE_CLIENT)) {
                Notification<Client> clientNotification = (new ClientInputDialog(new JPanel())).getClient();
                if (!clientNotification.hasErrors()){
                    System.out.println("update client: " + clientNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, clientNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + UPDATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class FindAllClientsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userView.setData("all clients");
        }
    }

    private class UpdateUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, UPDATE_USER)){
                Notification<User> userNotification = (new UserInputDialog(new JPanel())).getUser();
                if (!userNotification.hasErrors()){
                    User user = userNotification.getResult();
                    if (user.isAdmin()){
                        user.addRole(roleRightsService.getRoleByTitle(ADMINISTRATOR));
                    }
                    System.out.println("update client: " + userNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + UPDATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class InsertUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, CREATE_USER)){
                Notification<User> userNotification = (new UserInputDialog(new JPanel())).getUser();
                if (!userNotification.hasErrors()){
                    User user = userNotification.getResult();
                    if (user.isAdmin()){
                        user.addRole(roleRightsService.getRoleByTitle(ADMINISTRATOR));
                    }
                    System.out.println("insert user: " + userNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + CREATE_USER, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, DELETE_USER)){
                Notification<User> userNotification = (new UserInputDialog(new JPanel())).getUser();
                if (!userNotification.hasErrors()){
                    System.out.println("delete user: " + userNotification.getResult().getId());
                }
                else{
                    JOptionPane.showMessageDialog(userView, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + DELETE_USER, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ListUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            userView.setData("all users");
        }
    }

    private class ViewActivityActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, VIEW_ACTIVITY)){

            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + VIEW_ACTIVITY, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
