package controller.OperationsController;

import model.Account;
import model.Transfer;
import model.User;
import model.builder.ActionBuilder;
import model.validator.Notification;
import service.AccountService;
import service.ActionService;
import service.RoleRightsService;
import service.TransferService;
import view.InputDialog.TransferInputDialog;
import view.OperationView.ClientOperationsView;
import view.OperationView.MiscOperationsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import static database.Constants.Rights.CREATE_ACCOUNT;
import static database.Constants.Rights.MAKE_TRANSFER;
import static database.Constants.Rights.PAY_BILL;

public class MiscOperationsController {
    private User currentUser;
    private final MiscOperationsView view;
    private final RoleRightsService roleRightsService;
    private AccountService accountService;
    private TransferService transferService;
    private ActionService actionService;

    public MiscOperationsController(MiscOperationsView view, RoleRightsService roleRightsService, AccountService accountService, TransferService transferService, ActionService actionService) {
        this.view = view;
        this.roleRightsService = roleRightsService;
        this.accountService = accountService;
        this.transferService = transferService;
        this.actionService = actionService;
        view.setBtnMakeTransferActionListener(new MakeTransferActionListener());
        view.setBtnPayBillActionListener(new PayBillActionListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
                            JOptionPane.showMessageDialog(view, notification.getFormattedErrors(), "invalid transfer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(view, findDestinationNotification.getFormattedErrors() + findSourceNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(view, transferNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + CREATE_ACCOUNT, "Security error", JOptionPane.ERROR_MESSAGE);
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

}
