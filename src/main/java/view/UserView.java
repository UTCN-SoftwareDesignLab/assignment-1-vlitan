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

    private JTextField  tfClientName;
    private JTextField  tfClienIdentityCardNumber;
    private JTextField  tfClientPersonalNumericalCode;
    private JTextField  tfClientAddress;

    private JTextField  tfSourceAccountId;
    private JTextField  tfDestinationAccountId;

    private JTextField tfClientId;
    private JComboBox<AccountType> cbType;
    private JTextField tfAmount;
    private JTextField tfOwnerId;

    private JButton btnFindAllAccounts;
    private JButton btnFindAllClients;

    private JButton btnUpdateAccount;
    private JButton btnUpdateClient;

    private JButton btnInsertAccount;
    private JButton btnInsertClient;

    private JButton btnMakeTransfer;
    private JButton btnPayBill;


    public UserView() throws HeadlessException {
        setSize(600, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        add(tfLoggedUsername);
        add(taData);

//        add(tfClientId);
//        add(tfClientName);
//        add(tfClienIdentityCardNumber);
//        add(tfClientPersonalNumericalCode);
//        add(tfClientAddress);

        add(btnInsertClient);
        add(btnFindAllClients);
        add(btnUpdateClient);

//        add(cbType);
//        add(tfAmount);
//        add(tfOwnerId);

        add(btnInsertAccount);
        add(btnFindAllAccounts);
        add(btnUpdateAccount);

//        add(tfSourceAccountId);
//        add(tfDestinationAccountId);

        add(btnMakeTransfer);
        add(btnPayBill);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfClientId = new JTextField("client id");
        tfLoggedUsername = new JTextField("logged username");
        taData = new JTextArea("data");

        tfSourceAccountId  = new JTextField("source account id");
        tfDestinationAccountId = new JTextField("destination account id");

        tfClientName = new JTextField("client name");
        tfClienIdentityCardNumber = new JTextField("client identity");
        tfClientPersonalNumericalCode = new JTextField("client personal code");
        tfClientAddress = new JTextField("client address");

        cbType = new JComboBox<>();
        tfAmount = new JTextField("account amount");
        tfOwnerId = new JTextField("owner id");

        btnFindAllAccounts = new JButton("find all accounts");
        btnFindAllClients = new JButton("find all clients");

        btnUpdateAccount = new JButton("update account");
        btnUpdateClient = new JButton("update client");

        btnInsertAccount = new JButton("insert account");
        btnInsertClient = new JButton("insert client");
        btnMakeTransfer = new JButton("make transfer");
        btnPayBill = new JButton("pay bill");
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

    public String getDestinationAccountId(){
        return tfDestinationAccountId.getText();
    }

    public String getSourceAccountId(){
        return tfSourceAccountId.getText();
    }

    public String getClientId(){
        return tfClientId.getText();
    }

    public String getClientName(){
        return tfClientName.getText();
    }

    public String getClientIdentityCardNumber(){
        return tfClienIdentityCardNumber.getText();
    }

    public String getClientAddress(){
        return tfClientAddress.getText();
    }

    public AccountType getAccountType(){
        return (AccountType)cbType.getSelectedItem();
    }

    public String getOwnerId(){
        return tfOwnerId.getText();
    }
}
