package controller.OperationsController;

import model.Account;
import model.User;
import model.builder.ActionBuilder;
import model.validator.Notification;
import service.AccountService;
import service.ActionService;
import service.RoleRightsService;
import view.InputDialog.AccountInputDialog;
import view.OperationView.AccountOperationsView;
import view.OperationView.ClientOperationsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.stream.Collectors;

import static database.Constants.Rights.CREATE_ACCOUNT;
import static database.Constants.Rights.DELETE_ACCOUNT;
import static database.Constants.Rights.UPDATE_ACCOUNT;

public class AccountOperationsController {
    private User currentUser;
    private final AccountOperationsView view;
    private final RoleRightsService roleRightsService;
    private final AccountService accountService;
    private final ActionService actionService;

    public AccountOperationsController(AccountOperationsView view, RoleRightsService roleRightsService, AccountService accountService, ActionService actionService) {
        this.view = view;
        this.roleRightsService = roleRightsService;
        this.accountService = accountService;
        this.actionService = actionService;
        view.setBtnInsertAccountActionListener(new InsertAccountActionListener());
        view.setBtnFindAllAccountsActionListener(new FindAllAccountsActionListener());
        view.setBtnUpdateAccountActionListener(new UpdateAccountActionListener());
        view.setBtnDeleteAccountActionListener(new DeleteAccountActionListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    private class FindAllAccountsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setOutputData(accountService.findAll().stream().map(Account::toString).collect(Collectors.joining("\n")));
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(view, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + UPDATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(view, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + DELETE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(view, accountNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + CREATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
