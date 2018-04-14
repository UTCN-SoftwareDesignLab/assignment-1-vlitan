package controller.OperationsController;

import model.Client;
import model.User;
import model.builder.ActionBuilder;
import model.validator.Notification;
import service.ActionService;
import service.ClientService;
import service.RoleRightsService;
import view.InputDialog.ClientInputDialog;
import view.OperationView.ClientOperationsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.stream.Collectors;

import static database.Constants.Rights.CREATE_CLIENT;
import static database.Constants.Rights.UPDATE_CLIENT;

public class ClientOperationsController {
    private User currentUser;
    private final ClientOperationsView view;
    private final RoleRightsService roleRightsService;
    private ClientService clientService;
    private ActionService actionService;

    public ClientOperationsController(ClientOperationsView view, RoleRightsService roleRightsService, ClientService clientService, ActionService actionService){
        this.view = view;
        this.roleRightsService = roleRightsService;
        this.clientService = clientService;
        this.actionService = actionService;
        view.setBtnInsertClientListener(new InsertClientListener());
        view.setBtnUpdateClientActionListener(new UpdateClientActionListener());
        view.setBtnFindAllClientsActionListener(new FindAllClientsActionListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(view, clientNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + CREATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(view, clientNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + UPDATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class FindAllClientsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setOutputData(clientService.findAll().stream().map(Client::toString).collect(Collectors.joining("\n")));
        }
    }

}
