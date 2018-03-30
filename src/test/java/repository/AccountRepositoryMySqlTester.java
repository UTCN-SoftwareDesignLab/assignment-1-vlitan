package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.Account;
import model.AccountType;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.bank.AccountRepository;
import repository.bank.AccountRepositoryMySql;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static database.Schema.TEST;
import static org.junit.Assert.assertEquals;

public class AccountRepositoryMySqlTester {
    private static final int INIT_COUNT = 3;
    private static AccountRepository repository;

    @BeforeClass
    public static void setup(){
        repository = new  AccountRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    @Before
    public void bootstrap() throws SQLException {
        Bootstrap.main(null);
    }

    @Test
    public void insertAndFindTest(){
        AccountType type = AccountType.CREDIT;
        int amount = 2;
        Date creationDate = new Date(1, 2, 3);
        int ownerId = 1;
        Account mockAccount = (new AccountBuilder())
                                .setAmount(amount)
                                .setCreationDate(creationDate)
                                .setOwnerId(ownerId)
                                .setType(type)
                                .build();
        repository.add(mockAccount);
        List<Account> accounts = repository.findAll();
        Account retrievedAccount = repository.findById(INIT_COUNT + 1);
        assertEquals(accounts.size(), INIT_COUNT + 1);
        assertEquals(retrievedAccount.getAmount(), mockAccount.getAmount());
    }

    @Test
    public void updateAndFindTest(){
        int amount = 2;
        Account retrievedAccount = repository.findById(2);
        retrievedAccount.setAmount(amount);
        repository.update(retrievedAccount);
        assertEquals(retrievedAccount.getAmount(), repository.findById(2).getAmount());
    }

    @Test
    public void deleteAndFindTest(){
        Account retrievedAccount = repository.findById(2);
        repository.delete(retrievedAccount);
        assertEquals(repository.findById(2).getId(), 0);
    }

    @Test
    public void findAllAccountsTest(){
        List<Account> accounts = repository.findAll();
        assertEquals(accounts.size(), INIT_COUNT);
    }

    @Test
    public void findByClientTest(){
        assertEquals(repository.findByClient((new ClientBuilder()).setId(1).build()).size(), 2);
    }


}
