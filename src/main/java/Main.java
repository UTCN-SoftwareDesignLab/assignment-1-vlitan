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
        RoleRightsService roleRightsService =  new RoleRightsServiceImpl(new RightsRolesRepositoryMySQL
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)).getConnection()));
        AccountService accountService = new AccountServiceMySql(new AccountRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        ActionService actionService = new ActionServiceMySql(new ActionRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        TransferService transferService = new TransferServiceImpl(new AccountRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))),
                new ActionRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        BillService billService = new BillServiceImpl(new ActionRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        ClientService clientService = new ClientServiceImpl(new ClientRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        UserService userService = new UserServiceMySql(new UserRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST))),
                new RightsRolesRepositoryMySQL (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)).getConnection())));

        ComponentFactory componentFactory = ComponentFactory.instance();
        LoginController loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        MainController mainController = new MainController(new UserView(), accountService, roleRightsService, actionService, transferService, billService, clientService, userService);
        loginController.addObserver(mainController);

     }
}