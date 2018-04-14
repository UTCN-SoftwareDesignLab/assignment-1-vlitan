import controller.LoginController;
import controller.MainController;
import controller.OperationsController.AccountOperationsController;
import controller.OperationsController.ClientOperationsController;
import controller.OperationsController.MiscOperationsController;
import controller.OperationsController.UserOperationsController;
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
import view.AdminView;
import view.LoginView;
import view.OperationViewFactory;
import view.UserView;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance();
        OperationViewFactory operationViewFactory = OperationViewFactory.instance();
        LoginController loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        AccountOperationsController accountOperationsController = new AccountOperationsController(operationViewFactory.getAccountOperationsView(),
                componentFactory.getRoleRightsService(),
                componentFactory.getAccountService(),
                componentFactory.getActionService());
        ClientOperationsController clientOperationsController = new ClientOperationsController(operationViewFactory.getClientOperationsView(),
                componentFactory.getRoleRightsService(),
                componentFactory.getClientService(),
                componentFactory.getActionService());
        MiscOperationsController miscOperationsController = new MiscOperationsController(operationViewFactory.getMiscOperationsView(),
                componentFactory.getRoleRightsService(),
                componentFactory.getAccountService(),
                componentFactory.getTransferService(),
                componentFactory.getActionService());
        UserOperationsController userOperationsController = new UserOperationsController(operationViewFactory.getUserOperationsView(),
                componentFactory.getRoleRightsService(),
                componentFactory.getUserService(),
                componentFactory.getActionService());

        MainController mainController = new MainController(componentFactory.getRoleRightsService(), new UserView(),  new AdminView(), accountOperationsController, clientOperationsController, miscOperationsController, userOperationsController);
        loginController.addObserver(mainController);


     }
}