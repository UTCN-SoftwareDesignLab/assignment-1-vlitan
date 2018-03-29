package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.Transfer;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import repository.bank.TransferRepository;
import repository.bank.TransferRepositoryMySql;

import java.sql.SQLException;
import java.util.List;

import static database.Schema.TEST;

public class TransferRepositoryMySqlTester {
    private static final int INIT_COUNT = 3;
    private static TransferRepository repository;

    @BeforeClass
    public static void setup(){
        repository = new TransferRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    @Before
    public void bootstrap() throws SQLException {
        Bootstrap.main(null);
    }

    public void addTransfer(){

    }
    public void updateTransfer(){

    }
    public void findAllTransfers(){

    }
    public void findTransferById(){

    }
    public void findByUser(){

    }
}
