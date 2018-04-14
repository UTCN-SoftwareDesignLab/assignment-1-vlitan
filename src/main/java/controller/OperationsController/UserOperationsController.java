package controller.OperationsController;

import model.Action;
import model.ListActivityDTO;
import model.User;
import model.validator.Notification;
import service.ActionService;
import service.ClientService;
import service.RoleRightsService;
import service.UserService;
import view.InputDialog.ListActivityInputDialog;
import view.InputDialog.UserInputDialog;
import view.OperationView.OperationsView;
import view.OperationView.UserOperationsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import static database.Constants.Rights.*;
import static database.Constants.Rights.DELETE_USER;
import static database.Constants.Rights.VIEW_ACTIVITY;
import static database.Constants.Roles.ADMINISTRATOR;

public class UserOperationsController {
    private final RoleRightsService roleRightsService;
    private final UserService userService;
    private final OperationsView view;
    private final ActionService actionService;
    private User currentUser;


    public UserOperationsController(UserOperationsView view, RoleRightsService roleRightsService, UserService userService, ActionService actionService){
        this.roleRightsService = roleRightsService;
        this.userService = userService;
        this.actionService = actionService;
        this.view = view;
        view.setBtnViewActivityActionListener(new ViewActivityActionListener());
        view.setBtnUpdateUserActionListener(new UpdateUserActionListener());
        view.setBtnInsertUserActionListener(new InsertUserActionListener());
        view.setBtnDeleteUserActionListener(new DeleteUserActionListener());
        view.setBtnListUsersActionListener(new ListUserActionListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(view, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + UPDATE_CLIENT, "Security error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(view, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + CREATE_USER, "Security error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(view, result.getFormattedErrors(), "invalid data", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(view, userNotification.getFormattedErrors(), "Data retrieval error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + DELETE_USER, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ListUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.setOutputData(userService.findAll().stream().map(User::toString).collect(Collectors.joining("\n")));
        }
    }

    private class ViewActivityActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (roleRightsService.hasRight(currentUser, VIEW_ACTIVITY)){
                Notification<ListActivityDTO> activityDTONotification = (new ListActivityInputDialog(new JPanel())).getActivityDTO();
                if (!activityDTONotification.hasErrors()){
                    view.setOutputData(actionService.getActionsByUserInInterval(activityDTONotification.getResult()).stream().map(Action::toString).collect(Collectors.joining("\n")));
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "you dont have the right to " + VIEW_ACTIVITY, "Security error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
