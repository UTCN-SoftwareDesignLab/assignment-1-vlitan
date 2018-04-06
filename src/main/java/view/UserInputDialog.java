package view;

import database.Constants;
import model.Account;
import model.AccountType;
import model.User;
import model.builder.UserBuilder;
import model.validator.Notification;

import javax.swing.*;
import java.awt.*;

public class UserInputDialog {

    private JTextField tfId;

    private JTextField tfUserName;
    private JCheckBox cbIsAdmin;
    private JPanel panel;
    private JTextField tfPassword;

    public UserInputDialog(JPanel panel){
        this.panel = panel;
        initialiseComponents();
        addComponents();
    }

    private void addComponents(){
        panel.add(new Label("user id"));
        panel.add(tfId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("username"));
        panel.add(tfUserName);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("is admin?"));
        panel.add(cbIsAdmin);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("password"));
        panel.add(tfPassword);
    }

    private void initialiseComponents() {
        panel = new JPanel();
        tfId = new JTextField("   0");
        tfUserName = new JTextField(10);
        cbIsAdmin = new JCheckBox();
        tfPassword = new JTextField(10);
    }

    public Notification<User> getUser(){
        Notification<User> userNotification = new Notification<>();
        int result;
        boolean parsed = false;
        do {
            result = JOptionPane.showConfirmDialog(null, panel, "please enter account data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    User user = new UserBuilder()
                    .setId(Integer.parseInt(tfId.getText()))
                    .setUsername(tfUserName.getText())
                    .setAdmin(cbIsAdmin.isSelected())
                    .build();
                    userNotification.setResult(user);
                    parsed = true;
                    return userNotification;
                } catch (NumberFormatException e) {
                    parsed = false;
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                userNotification.addError("operation canceled");
            }
        } while (result != JOptionPane.CANCEL_OPTION && (parsed == false));
        return userNotification;
    }
}
