import controller.LoginController;
import controller.MainController;
import model.Account;
import model.AccountType;
import model.builder.AccountBuilder;
import view.LoginView;
import view.UserView;

import java.sql.Date;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance();
        LoginController loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        MainController mainController = new MainController(new UserView());
        loginController.addObserver(mainController);

    }
}
