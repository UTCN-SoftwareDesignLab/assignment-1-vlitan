package repository;

import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMySql implements UserRepository {

    private JDBConnectionWrapper connectionWrapper;

    public UserRepositoryMySql(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper = connectionWrapper;
    }


    @Override
    public boolean addUser(User user) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO User (`id`,\n" +
                            "`username`,\n" +
                            "`password`\n" +
                            ")VALUES(null, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2,user.getPassword());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `User`\n" +
                            "SET\n" +
                            "`username` = ?,\n" +
                            "`password` = ?\n" +
                            "WHERE `id` = ?;\n" +
                            "SELECT * FROM User;");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserById(User user) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM `User`\n" +
                            "WHERE `id` = ?;");
            statement.setInt(1, user.getId());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAllUsers() {
        Connection connection = connectionWrapper.getConnection();

        List<User> users = new ArrayList<User>();
        try {

            PreparedStatement statement = connection.prepareStatement(
            "SELECT `User`.`id`,\n" +
                    "   `User`.`username`,\n" +
                    "   `User`.`password`\n" +
                    "FROM `User`;");
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                users.add(getUserFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserById(int id) {
        Connection connection = connectionWrapper.getConnection();

        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT `User`.`id`,\n" +
                            "   `User`.`username`,\n" +
                            "   `User`.`password`\n" +
                            "FROM `User`" +
                            "WHERE `id` = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                user =  getUserFromResultSet(rs);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        return (new UserBuilder())
                .setId(id)
                .setPassword(password)
                .setUsername(username)
                .build();
    }
}
