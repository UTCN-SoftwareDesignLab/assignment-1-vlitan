package view;

import model.Account;
import model.AccountType;
import model.Right;
import model.builder.AccountBuilder;
import model.validator.Notification;

import javax.swing.*;

import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

public class AccountInputDialog{

    private JTextField tfId;
    private JComboBox<AccountType> cbType;
    private JTextField tfAmount;
    private JTextField tfOwnerId;
    private JOptionPane optionPane;
    private JPanel panel;

    public AccountInputDialog(JPanel panel){
        this.panel = panel;
        this.panel.setSize(300, 300);
        this.panel.setLayout(new BoxLayout(this.panel, Y_AXIS));
        initialiseComponents();
        addComponents();
    }

    private void addComponents(){
        panel.add(new Label("account id"));
        panel.add(tfId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("account type"));
        panel.add(tfAmount);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("owner id"));
        panel.add(tfOwnerId);
    }

    private void initialiseComponents() {
        panel = new JPanel();
        tfId = new JTextField("     0");
        cbType = new JComboBox<>(AccountType.values());
        tfAmount = new JTextField("     0");
        tfOwnerId = new JTextField("     0");
    }

    public Notification<Account> getAccount(){
        Notification<Account> accountNotification = new Notification<>();
        int result;
        boolean parsed = false;
        do {
            result = JOptionPane.showConfirmDialog(null, panel, "please enter account data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    Account account = (new AccountBuilder())
                        .setId(Integer.parseInt(tfId.getText()))
                        .setAmount(Integer.parseInt(tfAmount.getText()))
                        .setOwnerId(Integer.parseInt(tfOwnerId.getText()))
                        .setType(AccountType.valueOf(cbType.getSelectedItem().toString()))
                        .build();
                    accountNotification.setResult(account);
                    parsed = true;
                    return accountNotification;
                } catch (NumberFormatException e) {
                    parsed = false;
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                accountNotification.addError("operation canceled");
            }
        } while (result != JOptionPane.CANCEL_OPTION && (parsed == false));
        return accountNotification;
    }
}
