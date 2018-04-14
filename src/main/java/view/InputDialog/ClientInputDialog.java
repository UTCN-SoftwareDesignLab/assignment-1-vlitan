package view.InputDialog;

import model.Client;
import model.builder.ClientBuilder;
import model.validator.Notification;

import javax.swing.*;
import java.awt.*;

public class ClientInputDialog {

    private JTextField tfId;
    private JTextField  tfClientName;
    private JTextField  tfClientIdentityCardNumber;
    private JTextField  tfClientPersonalNumericalCode;
    private JTextField  tfClientAddress;

    private JPanel panel;

    public ClientInputDialog(JPanel panel){
        this.panel = panel;
        initialiseComponents();
        addComponents();
    }

    private void addComponents(){
        panel.add(new Label("id"));
        panel.add(tfId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("name"));
        panel.add(tfClientName);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("personal numerical code"));
        panel.add(tfClientPersonalNumericalCode);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("identity card"));
        panel.add(tfClientIdentityCardNumber);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("address"));
        panel.add(tfClientAddress);
    }

    private void initialiseComponents() {
        panel = new JPanel();
        tfId = new JTextField("0");
        tfClientName = new JTextField(20);
        tfClientIdentityCardNumber = new JTextField(12);
        tfClientPersonalNumericalCode = new JTextField(12);
        tfClientAddress = new JTextField(20);
    }

    public Notification<Client> getClient(){
        Notification<Client> clientNotification = new Notification<>();
        boolean parsed = false;
        int result;
        do {
            result = JOptionPane.showConfirmDialog(null, panel, "please enter client data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    Client client = new ClientBuilder()
                        .setId(Integer.parseInt(tfId.getText()))
                            .setName(tfClientName.getText())
                        .setAddress(tfClientAddress.getText())
                        .setPersonalNumericalCode(tfClientPersonalNumericalCode.getText())
                        .setIdentityCardNumber(tfClientIdentityCardNumber.getText())
                        .build();

                    clientNotification.setResult(client);
                    return clientNotification;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (result != JOptionPane.CANCEL_OPTION && parsed == false);
        return clientNotification;
    }
}
