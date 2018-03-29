package repository.bank;

import database.JDBConnectionWrapper;
import model.Account;
import model.AccountType;
import model.Client;
import model.builder.AccountBuilder;

import java.sql.*;
import java.util.ArrayList;
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
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Account (`id`,\n" +
                            "(`id`,\n" +
                            "`account_type`,\n" +
                            "`amount`,\n" +
                            "`creation_date`,\n" +
                            "`Client_id`)" +
                            "VALUES" +
                            "(null, ?, ?, ?, ?)");
            statement.setString(1, account.getType().toString());
            statement.setInt(2, account.getAmount());
            statement.setDate(3, account.getCreationDate());
            statement.setInt(3, account.getOwnerId());
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
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE account SET values (?, ?, ?, ?) WHERE ID = ?");
            statement.setString(1, account.getType().toString());
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
    public List<Account> findAllAccounts() {
        Connection connection = connectionWrapper.getConnection();
        List<Account> accounts = new ArrayList<Account>();

        try {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Account");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            accounts.add(getAccountFromResultSet(rs));
        }
        statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Account> findByClient(Client client) {
        Connection connection = connectionWrapper.getConnection();
        List<Account> accounts = new ArrayList<Account>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Account WHERE `Client_id` = ?");
            statement.setInt(1,client.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                accounts.add(getAccountFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findAccountById(int id) {
        Connection connection = connectionWrapper.getConnection();
        Account account = new Account();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Account WHERE `id` = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            account = getAccountFromResultSet(rs);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        AccountType type = AccountType.valueOf(rs.getString("type").toUpperCase());
        int amount = rs.getInt("amount");
        Date creationDate = rs.getDate("creation_date");
        int ownerId = rs.getInt("Client_id");
        return (new AccountBuilder())
                .setType(type)
                .setId(id)
                .setAmount(amount)
                .setCreationDate(creationDate)
                .setOwnerId(ownerId)
                .build();
    }
}
