import controller.LoginController;
import controller.MainController;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.Account;
import model.AccountType;
import model.builder.AccountBuilder;
import repository.bank.*;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import service.*;
import view.LoginView;
import view.UserView;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //this code has to be purged after evaluation
        ComponentFactory componentFactory = ComponentFactory.instance();
        LoginController loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        MainController mainController = new MainController(new UserView(),
                componentFactory.getAccountService(),
                componentFactory.getRoleRightsService(),
                componentFactory.getActionService(),
                componentFactory.getTransferService(),
                componentFactory.getBillService(),
                componentFactory.getClientService(),
                componentFactory.getUserService());
        loginController.addObserver(mainController);
     }
}