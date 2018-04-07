package view;

import model.Account;
import model.AccountType;
import model.Transfer;
import model.builder.AccountBuilder;
import model.builder.TransferBuilder;
import model.validator.Notification;

import javax.swing.*;
import java.awt.*;

public class TransferInputDialog {

    private JTextField tfAmount;
    private JTextField tfSourceId;
    private JTextField tfDestinationId;
    private JPanel panel;

    public TransferInputDialog(JPanel panel){
        this.panel = panel;
        initialiseComponents();
        addComponents();
    }

    private void addComponents(){
        panel.add(new Label("account id"));
        panel.add(tfSourceId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("amount"));
        panel.add(tfAmount);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("destination id"));
        panel.add(tfDestinationId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("source id"));
        panel.add(tfSourceId);
    }

    private void initialiseComponents() {
        panel = new JPanel();
        tfSourceId = new JTextField("    0");
        tfDestinationId = new JTextField("    0");
        tfAmount = new JTextField("    0");
        tfDestinationId = new JTextField("    0");
    }

    public Notification<Transfer> getTransfer(){
        Notification<Transfer> transferNotification = new Notification<>();
        int result;
        boolean parsed = false;
        do {
            result = JOptionPane.showConfirmDialog(null, panel, "please enter account data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {

                    Account sourceAccount = new AccountBuilder().setId(Integer.parseInt(tfSourceId.getText())).build();
                    Account destinationAccount = new AccountBuilder().setId(Integer.parseInt(tfDestinationId.getText())).build();
                    transferNotification.setResult( new TransferBuilder()
                            .setDestinationAccount(destinationAccount)
                            .setSourceAccount(sourceAccount)
                            .setAmout(Integer.parseInt(tfAmount.getText()))
                            .build());
                    parsed = true;
                    return transferNotification;
                } catch (NumberFormatException e) {
                    parsed = false;
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                transferNotification.addError("operation canceled");
            }
        } while (result != JOptionPane.CANCEL_OPTION && (parsed == false));
        return transferNotification;
    }
}
