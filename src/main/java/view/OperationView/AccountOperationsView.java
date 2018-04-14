package view.OperationView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AccountOperationsView extends OperationsView {
    private JButton btnFindAllAccounts;
    private JButton btnUpdateAccount;
    private JButton btnInsertAccount;
    private JButton btnDeleteAccount;

    void initialiseComponents(){
        btnDeleteAccount = new JButton("delete account");
        btnFindAllAccounts = new JButton("find all accounts");
        btnInsertAccount = new JButton("insert account");
        btnUpdateAccount = new JButton("update account");
    }

    void addComponents(){
        add(btnInsertAccount);
        add(btnFindAllAccounts);
        add(btnUpdateAccount);
        add(btnDeleteAccount);
    }

    public void setBtnDeleteAccountActionListener(ActionListener actionListener){
        btnDeleteAccount.addActionListener(actionListener);
    }

    public void setBtnFindAllAccountsActionListener(ActionListener actionListener){
        btnFindAllAccounts.addActionListener(actionListener);
    }


    public void setBtnUpdateAccountActionListener(ActionListener actionListener){
        btnUpdateAccount.addActionListener(actionListener);
    }


    public void setBtnInsertAccountActionListener(ActionListener actionListener){
        btnInsertAccount.addActionListener(actionListener);
    }
}
