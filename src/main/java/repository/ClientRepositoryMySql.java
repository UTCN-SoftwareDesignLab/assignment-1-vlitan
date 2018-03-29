package repository;

import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySql implements ClientRepository {

    private JDBConnectionWrapper connectionWrapper;

    public ClientRepositoryMySql(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper = connectionWrapper;
    }

    @Override
    public boolean addClient(Client client) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `bank_test`.`Client`\n" +
                            "(`id`,\n" +
                            "`name`,\n" +
                            "`identity_card_number`,\n" +
                            "`personal_numerical_code`,\n" +
                            "`address`)\n" +
                            "VALUES\n" +
                            "(null, ?, ?, ?, ?);");
            statement.setString(1, client.getName());
            statement.setString(2, client.getIdentityCardNumber());
            statement.setString(3, client.getPersonalNumericalCode());
            statement.setString(4, client.getAddress());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateClient(Client client) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                 "    UPDATE `bank_test`.`Client`\n" +
                    "    SET\n" +
                    "            `name` = ?,\n" +
                    "            `identity_card_number` = ?,\n" +
                    "            `personal_numerical_code` = ?,\n" +
                    "            `address` = ?\n" +
                    "    WHERE `id` = ?;");
            statement.setString(1, client.getName());
            statement.setString(2, client.getIdentityCardNumber());
            statement.setString(3, client.getPersonalNumericalCode());
            statement.setString(4, client.getAddress());
            statement.setInt(5, client.getId());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Client> findAllClients() {
        Connection connection = connectionWrapper.getConnection();

        List<Client> clients = new ArrayList<Client>();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `Client`;");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                clients.add(getClientFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public boolean deleteClientById(Client client) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM `Client`\n" +
                            "WHERE `id` = ?;");
            statement.setInt(1, client.getId());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findClientById(int id) {

        Connection connection = connectionWrapper.getConnection();

        Client client = new Client();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `Client`\n"+
                            "WHERE `id` = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                client =  getClientFromResultSet(rs);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        int     id = rs.getInt("id");
        String  name = rs.getString("name");
        String  identityCardNumber = rs.getString("identity_card_number");
        String  personalNumericalCode= rs.getString("personal_numerical_code");
        String  address= rs.getString("address");
        return (new ClientBuilder())
                .setId(id)
                .setName(name)
                .setIdentityCardNumber(identityCardNumber)
                .setAddress(address)
                .setPersonalNumericalCode(personalNumericalCode)
                .build();
    }
}
