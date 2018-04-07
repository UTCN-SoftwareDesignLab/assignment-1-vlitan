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
    private final AccountService accountService;
    private final ActionService actionService;
    private final BillService billService;
    private final TransferService transferService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AccountRepository accountRepository;
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
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        this.userRepository = new UserRepositoryMySql(connectionWrapper, this.rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySql(connectionWrapper);
        this.actionRepository = new ActionRepositoryMySql(connectionWrapper);
        this.clientRepository = new ClientRepositoryMySql(connectionWrapper);

        this.authenticationService = new AuthenticationServiceMySql(this.userRepository, this.rightsRolesRepository);

        accountService = new AccountServiceMySql(this.accountRepository);
        actionService = new ActionServiceMySql(this.actionRepository);
        billService = new BillServiceImpl(new ActionRepositoryMySql(
                (new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST)))));
        transferService = new TransferServiceImpl(this.accountRepository, this.actionRepository);
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
