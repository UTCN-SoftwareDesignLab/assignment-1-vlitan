package view;

import model.Account;
import model.AccountType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class UserView extends JFrame{
    private JTextField tfLoggedUsername;
    private JTextArea   taData;

    private JButton btnUpdateUser;
    private JButton btnInsertUser;
    private JButton btnDeleteUser;
    private JButton btnListUsers;
    private JButton btnViewActivity;

    private JButton btnFindAllAccounts;
    private JButton btnFindAllClients;

    private JButton btnUpdateAccount;
    private JButton btnUpdateClient;

    private JButton btnInsertAccount;
    private JButton btnInsertClient;

    private JButton btnMakeTransfer;
    private JButton btnPayBill;
    private JButton btnDeleteAccount;


    public UserView() throws HeadlessException {
        this.setVisible(false);
        setSize(600, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(tfLoggedUsername);
        add(taData);

        add(btnInsertClient);
        add(btnFindAllClients);
        add(btnUpdateClient);

        add(btnInsertAccount);
        add(btnFindAllAccounts);
        add(btnUpdateAccount);
        add(btnDeleteAccount);

        add(btnMakeTransfer);
        add(btnPayBill);

        add(btnUpdateUser);
        add(btnInsertUser);
        add(btnDeleteUser);
        add(btnListUsers);
        add(btnViewActivity);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        tfLoggedUsername = new JTextField("logged username");
        taData = new JTextArea("data");

        btnDeleteAccount = new JButton("delete account");
        btnFindAllAccounts = new JButton("find all accounts");
        btnInsertAccount = new JButton("insert account");
        btnUpdateAccount = new JButton("update account");
        btnUpdateClient = new JButton("update client");
        btnInsertClient = new JButton("insert client");
        btnFindAllClients = new JButton("find all clients");
        btnMakeTransfer = new JButton("make transfer");
        btnPayBill = new JButton("pay bill");
        btnUpdateUser = new JButton("update user");
        btnInsertUser = new JButton("insert user");
        btnDeleteUser = new JButton("delete user");
        btnListUsers = new JButton("list users");
        btnViewActivity = new JButton("list activity");
    }

    public void setBtnUpdateUserActionListener(ActionListener actionListener){
        btnUpdateUser.addActionListener(actionListener);
    }
    public void setBtnInsertUserActionListener(ActionListener actionListener){
        btnInsertUser.addActionListener(actionListener);
    }
    public void setBtnDeleteUserActionListener(ActionListener actionListener){
        btnDeleteUser.addActionListener(actionListener);
    }
    public void setBtnListUsersActionListener(ActionListener actionListener){
        btnListUsers.addActionListener(actionListener);
    }
    public void setBtnViewActivityActionListener(ActionListener actionListener){
        btnViewActivity.addActionListener(actionListener);
    }

    public void setBtnDeleteAccountActionListener(ActionListener actionListener){
        btnDeleteAccount.addActionListener(actionListener);
    }

    public void setBtnFindAllAccountsActionListener(ActionListener actionListener){
        btnFindAllAccounts.addActionListener(actionListener);
    }

    public void setBtnFindAllClientsActionListener(ActionListener actionListener){
        btnFindAllClients.addActionListener(actionListener);
    }

    public void setBtnUpdateAccountActionListener(ActionListener actionListener){
        btnUpdateAccount.addActionListener(actionListener);
    }

    public void setBtnUpdateClientActionListener(ActionListener actionListener){
        btnUpdateClient.addActionListener(actionListener);
    }

    public void setBtnInsertAccountActionListener(ActionListener actionListener){
        btnInsertAccount.addActionListener(actionListener);
    }

    public void setBtnInsertClientListener(ActionListener actionListener){
        btnInsertClient.addActionListener(actionListener);
    }

    public void setBtnMakeTransferActionListener(ActionListener actionListener){
        btnMakeTransfer.addActionListener(actionListener);
    }

    public void setBtnPayBillActionListener(ActionListener actionListener){
        btnPayBill.addActionListener(actionListener);
    }

    public void setLoggedUsername(String username){
        tfLoggedUsername.setText(username);
    }

    public void setData(String data){
        taData.setText(data);
    }

}
