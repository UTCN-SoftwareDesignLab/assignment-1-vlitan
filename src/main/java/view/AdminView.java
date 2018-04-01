package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {

    private JTextField  tfLoggedUsername;
    private JTextArea   taData;

    private JTextField  tfClientName;
    private JTextField  tfClienIdentityCardNumber;
    private JTextField  tfClientPersonalNumericalCode;
    private JTextField  tfClientAddress;



    private JButton btnLogin;
    private JButton btnRegister;

    public AdminView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
//        add(tfUsername);
//        add(tfPassword);
        add(btnLogin);
        add(btnRegister);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
//        tfUsername = new JTextField();
//        tfPassword = new JTextField();
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
    }
//
//    public String getUsername() {
//        return tfUsername.getText();
//    }
//
//    public String getPassword() {
//        return tfPassword.getText();
//    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }

    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        btnRegister.addActionListener(registerButtonListener);
    }
}
