import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.Action;
import repository.bank.*;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;

import java.sql.Connection;
import service.AuthenticationService;
import service.*;

import static database.Schema.TEST;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final RoleRightsService roleRightsService;
    private final AccountService accountService;
    private final ActionService actionService;
    private final TransferService transferService;
    private final BillService billService;
    private final ClientService clientService;
    private final UserService userService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AccountRepository accountRepository;

    public RoleRightsService getRoleRightsService() {
        return roleRightsService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public BillService getBillService() {
        return billService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public ActionRepository getActionRepository() {
        return actionRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    private final ActionRepository actionRepository;
    private final ClientRepository clientRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }
/*
*   RoleRightsService roleRightsService =  new RoleRightsServiceImpl(new RightsRolesRepositoryMySQL
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
* */
    private ComponentFactory() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST));
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        this.userRepository = new UserRepositoryMySql(connectionWrapper, this.rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySql(connectionWrapper);
        this.actionRepository = new ActionRepositoryMySql(connectionWrapper);
        this.clientRepository = new ClientRepositoryMySql(connectionWrapper);

        this.authenticationService = new AuthenticationServiceMySql(this.userRepository, this.rightsRolesRepository);

        this.accountService = new AccountServiceMySql(this.accountRepository);
        this.actionService = new ActionServiceMySql(this.actionRepository);
        this.billService = new BillServiceImpl(new ActionRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        this.transferService = new TransferServiceImpl(this.accountRepository, this.actionRepository);
        this.roleRightsService =  new RoleRightsServiceImpl(new RightsRolesRepositoryMySQL(connectionWrapper.getConnection()));
        this.clientService = new ClientServiceImpl(this.clientRepository);
        this.userService = new UserServiceMySql(new UserRepositoryMySql(connectionWrapper, new RightsRolesRepositoryMySQL (connectionWrapper.getConnection())));
    }



    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

}
