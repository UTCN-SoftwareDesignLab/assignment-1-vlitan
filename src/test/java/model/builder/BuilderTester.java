package model.builder;

import model.Account;
import model.AccountType;
import model.Client;
import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;

/*
* Tests all builders
* */
public class BuilderTester {
    @Test
    public void testAccountBuilder(){
        //init mock data
        int id = 3;
        AccountType type = AccountType.CREDIT;
        int amount = 23;
        Date date = new Date(1234, 3, 3);
        //create object
        Account account = (new AccountBuilder())
                .setAmount(amount)
                .setId(id)
                .setType(type)
                .setCreationDate(date)
                .build();
        //test value of fields
        assertTrue(account.getAmount() == amount);
        assertTrue(account.getId() == id);
        assertTrue(account.getType() == type);
        assertEquals(account.getCreationDate(), date);
    }

    @Test
    public void testClientBuilder(){
        //init mockup data
        int     id = 3;
        String  name = "cicala345ca";
        String  identityCardNumber = "12werftghjmk,l./";
        String  personalNumericalCode = "'!d_34";
        String  address = "1q23w4e5,l;";
        //create object
        Client  client = (new ClientBuilder())
                .setId(id)
                .setName(name)
                .setIdentityCardNumber(identityCardNumber)
                .setPersonalNumericalCode(personalNumericalCode)
                .setAddress(address)
                .build();
        //test value of fields
        assertTrue(client.getId() == id);
        assertTrue(client.getName().equals(name));
        assertTrue(client.getIdentityCardNumber().equals(identityCardNumber));
        assertTrue(client.getPersonalNumericalCode().equals(personalNumericalCode));
        assertTrue(client.getAddress().equals(address));
    }

    @Test
    public void testUserBuilder(){
        int id = 35;
        String username = "username";
        String password = "32redf'@";

        User user = (new UserBuilder())
                .setId(id)
                .setPassword(password)
                .setUsername(username)
                .build();

        assertTrue(user.getId() == id);
        assertTrue(user.getPassword().equals(password));
        assertTrue(user.getUsername().equals(username));
    }
}
