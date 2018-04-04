package view;

import model.Account;
import model.AccountType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

import static javax.swing.BoxLayout.Y_AXIS;

public class UserView extends JFrame{
    private JTextField tfLoggedUsername;
    private JTextArea   taData;



    private JTextField  tfSourceAccountId;
    private JTextField  tfDestinationAccountId;

    private JTextField tfClientId;


    private JTextField tfStartActivityDate;
    private JTextField tfEndActivityDate;
    private JButton    btnUpdateUser;
    private JButton    btnInsertUser;
    private JButton    btnDeleteUser;
    private JButton    btnListUsers;
    private JButton    btnViewActivity;

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
        setSize(600, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(tfLoggedUsername);
        add(taData);

        add(tfClientId);


        add(btnInsertClient);
        add(btnFindAllClients);
        add(btnUpdateClient);

        add(btnInsertAccount);
        add(btnFindAllAccounts);
        add(btnUpdateAccount);
        add(btnDeleteAccount);

        add(tfSourceAccountId);
        add(tfDestinationAccountId);

        add(btnMakeTransfer);
        add(btnPayBill);

        add(tfUserName);
        add(tfUserId);
        add(tfStartActivityDate);
        add(tfEndActivityDate);
        add(btnUpdateUser);
        add(btnInsertUser);
        add(btnDeleteUser);
        add(btnListUsers);
        add(btnViewActivity);
        add(cbIsAdmin);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfClientId = new JTextField("client id");
        tfLoggedUsername = new JTextField("logged username");
        taData = new JTextArea("data");

        tfSourceAccountId  = new JTextField("source account id");
        tfDestinationAccountId = new JTextField("destination account id");

        btnDeleteAccount = new JButton("delete account");

        btnFindAllAccounts = new JButton("find all accounts");
        btnFindAllClients = new JButton("find all clients");

        btnUpdateAccount = new JButton("update account");
        btnUpdateClient = new JButton("update client");

        btnInsertAccount = new JButton("insert account");
        btnInsertClient = new JButton("insert client");
        btnMakeTransfer = new JButton("make transfer");
        btnPayBill = new JButton("pay bill");

        tfUserName = new JTextField("username");
        tfUserId = new JTextField("user id");
        tfStartActivityDate = new JTextField("start activity date");
        tfEndActivityDate = new JTextField("end activity date");
        btnUpdateUser = new JButton("update user");
        btnInsertUser = new JButton("insert user");
        btnDeleteUser = new JButton("delete user");
        btnListUsers = new JButton("list users");
        btnViewActivity = new JButton("list activity");
        cbIsAdmin = new JCheckBox();

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
        btnInsertAccount.addActionListener(actionListener);
    }

    public void setBtnInsertAccountActionListener(ActionListener actionListener){
        btnUpdateClient.addActionListener(actionListener);
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

    public String getUserId(){
        return tfUserId.getText();
    }

    public String getStartActivityDate(){
        return tfStartActivityDate.getText();
    }

    public String getEndActivityDate(){
        return tfEndActivityDate.getText();
    }

    public boolean getIsAdmin(){
        return cbIsAdmin.isSelected();
    }

    public String getUsername(){
        return tfUserName.getText();
    }

    public void setLoggedUsername(String username){
        tfLoggedUsername.setText(username);
    }

    public void setData(String data){
        taData.setText(data);
    }

    public String getDestinationAccountId(){
        return tfDestinationAccountId.getText();
    }

    public String getSourceAccountId(){
        return tfSourceAccountId.getText();
    }

    public String getClientId(){
        return tfClientId.getText();
    }

    public AccountType getAccountType(){
        return (AccountType)cbType.getSelectedItem();
    }

    public String getOwnerId(){
        return tfOwnerId.getText();
    }
}
