package repository.bank;

import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMySql implements UserRepository {

    private JDBConnectionWrapper connectionWrapper;
    private final RightsRolesRepository rightsRolesRepository;

    public UserRepositoryMySql(JDBConnectionWrapper connectionWrapper, RightsRolesRepository rightsRolesRepository){
        this.connectionWrapper = connectionWrapper;
        this.rightsRolesRepository = rightsRolesRepository;
    }


    @Override
    public boolean add(User user) {
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
    public boolean update(User user) {
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
    public boolean deleteById(User user) {
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
    public  Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        Connection connection = connectionWrapper.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement( "Select * from `User` where `username`=? and `password`= ?;");
            statement.setString(1,username);
            statement.setString(2, password);

            ResultSet userResultSet = statement.executeQuery();
            userResultSet.next();

            User user = new UserBuilder()
                    .setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                    .build();
            findByUsernameAndPasswordNotification.setResult(user);
            return findByUsernameAndPasswordNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Invalid email or password!");
            return findByUsernameAndPasswordNotification;
        }
    }

    @Override
    public List<User> findAll() {
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
    public Notification<User> findById(int id) {
        Connection connection = connectionWrapper.getConnection();
        Notification<User> findByIdNotification = new Notification<>();
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
            if (rs.next())
            {
                user =  getUserFromResultSet(rs);
            }
            findByIdNotification.setResult(user);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            findByIdNotification.addError("User not found");
        }
        return findByIdNotification;
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
