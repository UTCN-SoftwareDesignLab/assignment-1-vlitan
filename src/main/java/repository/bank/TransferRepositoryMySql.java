package repository.bank;

import database.JDBConnectionWrapper;
import model.Account;
import model.Transfer;
import model.User;
import model.builder.TransferBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferRepositoryMySql implements TransferRepository{

    private JDBConnectionWrapper connectionWrapper;
    private AccountRepository accountRepository;
    public TransferRepositoryMySql(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper = connectionWrapper;
        accountRepository = new AccountRepositoryMySql(connectionWrapper);
    }

    @Override
    public boolean addTransfer(Transfer transfer) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Account (`id`,\n" +
                            "(`id`,\n" +
                            "`amount`,\n" +
                            "`source_account_id`,\n" +
                            "`destination_account_id`,\n" +
                            "`date`,\n" +
                            "`User_id`)"+
                            "VALUES" +
                            "(null, ?, ?, ?, ?, ?)");
            statement.setInt(1, transfer.getAmount());
            statement.setInt(2, transfer.getSourceAccount().getId());
            statement.setInt(3, transfer.getDestinationAccount().getId());
            statement.setDate(4, transfer.getDate());
            statement.setInt(5, transfer.getUserId());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateTransfer(Transfer transfer) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "   UPDATE `bank_test`.`Transfer`\n" +
                            "    SET\n" +
                            "       `amount` = ?,\n" +
                            "       `source_account_id` = ?,\n" +
                            "       `destination_account_id` = ?,\n" +
                            "       `date` = ?,\n" +
                            "    WHERE `id` = ?");
            statement.setInt(1, transfer.getAmount());
            statement.setInt(2, transfer.getSourceAccount().getId());
            statement.setInt(3, transfer.getDestinationAccount().getId());
            statement.setDate(4, transfer.getDate());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Transfer> findAllTransfers() {
        Connection connection = connectionWrapper.getConnection();
        List<Transfer> transfers = new ArrayList<Transfer>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Transfer");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                transfers.add(getTransferFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }

    @Override
    public Transfer findTransferById(int id) {
        Connection connection = connectionWrapper.getConnection();
        Transfer transfer = new Transfer();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Transfer WHERE `id` = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            transfer = getTransferFromResultSet(rs);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfer;
    }

    @Override
    public List<Transfer> findByUser(User user) {
        Connection connection = connectionWrapper.getConnection();
        List<Transfer> transfers = new ArrayList<Transfer>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Transfer WHERE `User_id` = ?");
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                transfers.add(getTransferFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }

    private Transfer getTransferFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        int amount = rs.getInt("id");
        Account sourceAccount = accountRepository.findAccountById(rs.getInt("source_account_id"));
        Account destinationAccount = accountRepository.findAccountById(rs.getInt("destination_account_id"));
        Date date = rs.getDate("date");
        int userId = rs.getInt("User_id");
        return (new TransferBuilder())
                .setId(id)
                .setAmount(amount)
                .setSourceAccount(sourceAccount)
                .setDestinationAccount(destinationAccount)
                .setDate(date)
                .setUserid(userId)
                .build();
    }
}
