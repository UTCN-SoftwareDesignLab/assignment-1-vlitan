package repository;

import database.JDBConnectionWrapper;
import database.Schema;
import model.Account;
import model.Client;
import model.Transfer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AccountRepositoryMySql implements AccountRepository{

    private JDBConnectionWrapper connectionWrapper;

    public AccountRepositoryMySql(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper = connectionWrapper;
    }

    @Override
    public boolean addAccount(Account account) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO account values (null, ?, ?, ?)");
            statement.setString(1, "test");//TODO get string from AccountType enum
            statement.setInt(2, account.getAmount());
            statement.setDate(3, account.getCreationDate());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {

        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("     \"UPDATE account SET values (?, ?, ?, ?) WHERE ID = ?\"");
            statement.setString(1, "test");//TODO get string from AccountType enum
            //TODO
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Account account) {
        return false;
    }

    @Override
    public boolean dropAllAccounts() {
        return false;
    }

    @Override
    public List<Account> findAllAccounts() {
        return null;
    }

    @Override
    public Account findAccountById(int id) {
        return null;
    }
}
