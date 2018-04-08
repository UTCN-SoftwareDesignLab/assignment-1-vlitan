package controller;

import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.*;
import model.Action;
import model.builder.ActionBuilder;
import model.validator.*;
import repository.bank.UserRepository;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import service.*;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import static database.Constants.Rights.*;
import static database.Constants.Roles.ADMINISTRATOR;

public class MainController implements Observer{
    private User currentUser;
    private UserView userView;
    private RoleRightsService roleRightsService;
    private AccountService accountService;
    private ActionService actionService;
    private TransferService transferService;
    private BillService billService;
    private ClientService clientService;
    private UserService userService;

    //Hate to do this, but now its too late
    public MainController(UserView userView,
                             AccountService accountService,
                             RoleRightsService roleRightsService,
                             ActionService actionService,
                             TransferService transferService,
                             BillService billService,
                             ClientService clientService,
                             UserService userService){
        this.userView = userView;
        this.accountService = accountService;
        this.roleRightsService = roleRightsService;
        this.actionService = actionService;
        this.transferService = transferService;
        this.billService = billService;
        this.clientService = clientService;
        this.userService = userService;

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
            userView.setData(accountService.findAll().stream().map(Account::toString).collect(Collectors.joining("\n")));
        }
    }

    private class UpdateAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, UPDATE_ACCOUNT)){
                Notification<Account> accountNotification = (new AccountInputDialog(new JPanel())).getAccount();
                if (!accountNotification.hasErrors()){
                    System.out.println("update account: " + accountNotification.getResult().getId());
                    Notification<Boolean> result = accountService.update(accountNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        actionService.addAction(new ActionBuilder()
                                .setUserId(currentUser.getId())
                                .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                                .setDescription("updated account")
                                .build());
                    }
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
                    Notification<Boolean> result = accountService.delete(accountNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        actionService.addAction(new ActionBuilder()
                                .setUserId(currentUser.getId())
                                .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                                .setDescription("deleted account")
                                .build());
                    }
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
                    Account account = accountNotification.getResult();
                    account.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
                    Notification<Boolean> result = accountService.add(accountNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        actionService.addAction(new ActionBuilder()
                                .setUserId(currentUser.getId())
                                .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                                .setDescription("inserted account")
                                .build());
                    }
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
                Notification<Transfer> transferNotification = new TransferInputDialog(new JPanel()).getTransfer();
                if (!transferNotification.hasErrors()){
                    Transfer transfer = transferNotification.getResult();
                    Notification<Account> findSourceNotification = accountService.findById(transfer.getSourceAccount().getId());
                    Notification<Account> findDestinationNotification = accountService.findById(transfer.getDestinationAccount().getId());
                    if (!findDestinationNotification.hasErrors() && !findSourceNotification.hasErrors()){
                        transfer.setSourceAccount(findSourceNotification.getResult());
                        transfer.setDestinationAccount(findDestinationNotification.getResult());
                        transfer.setUserId(currentUser.getId());
                        transfer.setDate(new Date(Calendar.getInstance().getTime().getTime()));
                        Notification<Boolean> notification = transferService.makeTransfer(transfer);
                        if (notification.hasErrors()){
                            JOptionPane.showMessageDialog(userView, notification.getFormattedErrors(), "invalid transfer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(userView, findDestinationNotification.getFormattedErrors() + findSourceNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(userView, transferNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + CREATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class PayBillActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleRightsService.hasRight(currentUser, PAY_BILL)){
                actionService.addAction(new ActionBuilder()
                        .setUserId(currentUser.getId())
                        .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                        .setDescription("payed a bill ;) ")
                        .build());
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
                    Notification<Boolean> result = clientService.add(clientNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        actionService.addAction(new ActionBuilder()
                                .setUserId(currentUser.getId())
                                .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                                .setDescription("inserted client")
                                .build());
                    }
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
                    Notification<Boolean> result = clientService.update(clientNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        actionService.addAction(new ActionBuilder()
                                .setUserId(currentUser.getId())
                                .setDate(new Date(Calendar.getInstance().getTime().getTime()))
                                .setDescription("updated client")
                                .build());
                    }
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
            userView.setData(clientService.findAll().stream().map(Client::toString).collect(Collectors.joining("\n")));
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
                    Notification<Boolean> result = userService.update(userNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
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
                    System.out.println("update client: " + userNotification.getResult().getId());
                    Notification<Boolean> result = userService.add(userNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
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
                    User user = userNotification.getResult();
                    if (user.isAdmin()){
                        user.addRole(roleRightsService.getRoleByTitle(ADMINISTRATOR));
                    }
                    System.out.println("update client: " + userNotification.getResult().getId());
                    Notification<Boolean> result = userService.deleteById(userNotification.getResult());
                    if (result.hasErrors()){
                        JOptionPane.showMessageDialog(userView, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
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
            userView.setData(userService.findAll().stream().map(User::toString).collect(Collectors.joining("\n")));
        }
    }

    private class ViewActivityActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, VIEW_ACTIVITY)){
                Notification<ListActivityDTO> activityDTONotification = (new ListActivityInputDialog(new JPanel())).getActivityDTO();
                if (!activityDTONotification.hasErrors()){
                    userView.setData(actionService.getActionsByUserInInterval(activityDTONotification.getResult()).stream().map(Action::toString).collect(Collectors.joining("\n")));
                }
            }
            else{
                JOptionPane.showMessageDialog(userView, "you dont have the right to " + VIEW_ACTIVITY, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
