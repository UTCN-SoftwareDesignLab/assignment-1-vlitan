package view.OperationView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientOperationsView extends OperationsView {
    private JButton btnFindAllClients;
    private JButton btnUpdateClient;
    private JButton btnInsertClient;

    @Override
    void addComponents() {
        add(btnInsertClient);
        add(btnFindAllClients);
        add(btnUpdateClient);
    }

    @Override
    void initialiseComponents() {
        btnUpdateClient = new JButton("update client");
        btnInsertClient = new JButton("insert client");
        btnFindAllClients = new JButton("find all clients");
    }

    public void setBtnFindAllClientsActionListener(ActionListener actionListener){
        btnFindAllClients.addActionListener(actionListener);
    }

    public void setBtnUpdateClientActionListener(ActionListener actionListener){
        btnUpdateClient.addActionListener(actionListener);
    }

    public void setBtnInsertClientListener(ActionListener actionListener){
        btnInsertClient.addActionListener(actionListener);
    }
}
