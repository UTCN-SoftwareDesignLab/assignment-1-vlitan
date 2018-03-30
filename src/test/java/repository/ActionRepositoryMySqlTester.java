package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.Account;
import model.Action;
import model.Transfer;
import model.builder.AccountBuilder;
import model.builder.ActionBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.bank.ActionRepository;
import repository.bank.ActionRepositoryMySql;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static database.Schema.TEST;
import static org.junit.Assert.assertNotNull;

public class ActionRepositoryMySqlTester {
    private static final int INIT_COUNT = 3;
    private static ActionRepository repository;

    @BeforeClass
    public static void setup(){
        repository = new ActionRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    @Before
    public void bootstrap() throws SQLException {
        Bootstrap.main(null);
    }
    @Test
    public void addAndFindTest(){
        String description = "testDescription";
        Date date = new Date(1,2,3);
        int userId = 2;
        Action mockAction = (new ActionBuilder())
                .setDescription(description)
                .setDate(date)
                .setUserId(userId)
                .build();
        repository.addAction(mockAction);
        Action retrievedAction = repository.findActionById(INIT_COUNT + 1);
        assertNotNull(retrievedAction);
        assertTrue(retrievedAction.getId() > 0);
    }

    @Test
    public void updateAndFindAction(){
        String description = "testDescription";
        Action retrievedAction = repository.findActionById(2);
        retrievedAction.setDescription(description);
        repository.updateAction(retrievedAction);
        retrievedAction = repository.findActionById(2);
        assertEquals(retrievedAction.getDescription(), description);
    }

    @Test
    public void findAllActionsTest(){
        List<Action> actions = repository.findAllActions();
        assertEquals(actions.size(), INIT_COUNT);
    }


    @Test
    public void findByUserTest(){
        Action retrievedAction = repository.findActionById(1);
        assertNotNull(retrievedAction);
        assertEquals(retrievedAction.getId(), 1);
    }
}
