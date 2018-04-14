package view.OperationView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserOperationsView extends OperationsView{
    private JButton btnUpdateUser;
    private JButton btnInsertUser;
    private JButton btnDeleteUser;
    private JButton btnListUsers;
    private JButton btnViewActivity;


    @Override
    void addComponents() {
        add(btnUpdateUser);
        add(btnInsertUser);
        add(btnDeleteUser);
        add(btnListUsers);
        add(btnViewActivity);
    }

    @Override
    void initialiseComponents() {
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


}
