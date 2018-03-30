package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.User;
import model.builder.UserBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.bank.UserRepositoryMySql;

import java.util.List;

import static database.Schema.TEST;
import static org.junit.Assert.*;

public class UserRepositoryMySqlTester {
    private static UserRepositoryMySql repository;
    private static final int INITIAL_COUNT = 4;

     @BeforeClass
    public static void setupClass()  {
         repository = new UserRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    @Before
    public void bootstrap()throws Exception{
        Bootstrap.main(null) ; // TODO add parameters to this
    }
    //TODO make this check against constants and insert also those constants in bootstrap
    @Test
    public void testSelect()throws Exception{
        List<User> users = repository.findAll();
        assertTrue(users.size() == INITIAL_COUNT);
    }

    @Test
    public void testFindUserById(){
        User user = repository.findById(2);
        assertEquals(user.getUsername(), "user-mihai");
        assertEquals(user.getPassword(),"pass2");
        assertEquals(user.getId(), 2);
    }

    @Test
    public void testUpdateUser(){
        User user = repository.findById(2);
        user.setUsername("newname");
        user.setPassword("newpass");
        repository.update(user);
        assertEquals(user.getUsername(), "newname");
        assertEquals(user.getPassword(),"newpass");
        assertEquals(user.getId(), 2);
    }

    @Test
    public void testDeleteUser(){
        repository.deleteById(repository.findById(2));
        assertEquals(repository.findById(2).getId(), 0);
    }

    @Test
    public void testInsert(){
        List<User> users = repository.findAll();
        repository.add((new UserBuilder()).setUsername("logofat").setPassword("auri").build());
        User user = repository.findById(users.size() + 1);
        assertNotNull(user);
    }


}
