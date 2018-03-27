package model.validator;

import model.Account;
import model.Client;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ValidatorTester {
    private static User user;
    private static Account account;
    private static Client client;

    //TODO
    @BeforeClass
    public static void setupClass() throws Exception {
        user = new User();
        account = new Account();
        client = new Client();
    }

    @Test
    public void testUserValidator(){
        //TODO
    }

    @Test
    public void testAccountValidator(){
        //TODO
    }

    @Test
    public void testClientValidator(){
        //TODO
    }

}
