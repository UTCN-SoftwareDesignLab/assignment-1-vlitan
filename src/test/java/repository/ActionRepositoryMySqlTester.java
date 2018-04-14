package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.*;
import model.builder.AccountBuilder;
import model.builder.ActionBuilder;
import model.builder.UserBuilder;
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
    private static final int INIT_COUNT = 4;
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
        repository.add(mockAction);
        Action retrievedAction = repository.findById(INIT_COUNT + 1);
        assertNotNull(retrievedAction);
        assertTrue(retrievedAction.getId() > 0);
    }

    @Test
    public void updateAndFindAction(){
        String description = "testDescription";
        Action retrievedAction = repository.findById(2);
        retrievedAction.setDescription(description);
        repository.update(retrievedAction);
        retrievedAction = repository.findById(2);
        assertEquals(retrievedAction.getDescription(), description);
    }

    @Test
    public void findAllActionsTest(){
        List<Action> actions = repository.findAll();
        assertEquals(actions.size(), INIT_COUNT);
    }

    @Test
    public void findByUserInIntervalTest(){
        Date start = new Date(15, 1, 1);
        Date end = new Date(45, 1, 2);
        User user = (new UserBuilder()).setId(2).build();
        List<Action> actions = repository.findByUserInInterval(new ListActivityDTO(user.getId(), start, end));
        assertEquals(1, actions.size());
        assertEquals(actions.get(0).getDescription(), "mock description2");
    }

    @Test
    public void findByUserTest(){
        Action retrievedAction = repository.findById(1);
        assertNotNull(retrievedAction);
        assertEquals(retrievedAction.getId(), 1);
    }
}
