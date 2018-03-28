package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import database.Schema;
import model.Account;
import model.Client;
import model.User;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import static database.Schema.TEST;

public class UserRepositoryMySqlTester {
    private static UserRepositoryMySql repository;
    private static final int INITIAL_COUNT = 4;
     @BeforeClass
    public static void setupClass() throws Exception {
         Bootstrap.main(null); // TODO add parameters to this
         repository = new UserRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    //TODO make this check against constants and insert also those constants in bootstrap
    @Test
    public void testSelect()throws Exception{
        Bootstrap.main(null); // TODO add parameters to this
        List<User> users = repository.findAllUsers();
        assertTrue(users.size() == INITIAL_COUNT);
    }

    @Test
    public void testFindUserById(){
        User user = repository.findUserById(2);
        assertEquals(user.getUsername(), "user-mihai");
        assertEquals(user.getPassword(),"pass2");
        assertEquals(user.getId(), 2);
    }

    @Test
    public void testInsert(){
        repository.addUser((new UserBuilder()).setUsername("logofat").setPassword("auri").build());

    }
}
