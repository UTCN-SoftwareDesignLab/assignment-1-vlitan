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

    private ComponentFactory() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST));
        ///create repos
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        this.userRepository = new UserRepositoryMySql(connectionWrapper, this.rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySql(connectionWrapper);
        this.actionRepository = new ActionRepositoryMySql(connectionWrapper);
        this.clientRepository = new ClientRepositoryMySql(connectionWrapper);
        //create services
        this.authenticationService = new AuthenticationServiceMySql(this.userRepository, this.rightsRolesRepository);
        this.accountService = new AccountServiceMySql(this.accountRepository);
        this.actionService = new ActionServiceMySql(this.actionRepository);
        this.billService = new BillServiceImpl(this.actionRepository);
        this.transferService = new TransferServiceImpl(this.accountRepository, this.actionRepository);
        this.roleRightsService =  new RoleRightsServiceImpl(this.rightsRolesRepository);
        this.clientService = new ClientServiceImpl(this.clientRepository);
        this.userService = new UserServiceMySql(this.userRepository);
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
