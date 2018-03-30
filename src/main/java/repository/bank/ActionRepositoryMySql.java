package repository.bank;

import database.JDBConnectionWrapper;
import model.Action;
import model.Transfer;
import model.User;
import model.builder.ActionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryMySql implements ActionRepository {

    private JDBConnectionWrapper connectionWrapper;
    private AccountRepository accountRepository;
    public ActionRepositoryMySql(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper = connectionWrapper;
        accountRepository = new AccountRepositoryMySql(connectionWrapper);
    }

    @Override
    public boolean add(Action action) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Action\n" +
                            "(`id`,\n" +
                            "`description`,\n" +
                            "`date`,\n" +
                            "`User_id`)"+
                            "VALUES" +
                            "(null, ?, ?, ?)");
            statement.setString(1, action.getDescription());
            statement.setDate(2, action.getDate());
            statement.setInt(3, action.getUserId());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Action action) {
        Connection connection = connectionWrapper.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "   UPDATE `Action`\n" +
                            "    SET\n" +
                            "       `description` = ?,\n" +
                            "       `date` = ?,\n" +
                            "       `User_id` = ?\n" +
                            "    WHERE `id` = ?");
            statement.setString(1, action.getDescription());
            statement.setDate(2, action.getDate());
            statement.setInt(3, action.getUserId());
            statement.setInt(4, action.getId());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Action> findAll() {
        Connection connection = connectionWrapper.getConnection();
        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Action");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                actions.add(getActionFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actions;
    }

    @Override
    public Action findById(int id) {
        Connection connection = connectionWrapper.getConnection();
        Action action = new Action();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Action WHERE `id` = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                action = getActionFromResultSet(rs);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action;
    }

    @Override
    public List<Action> findByUser(User user) {
        Connection connection = connectionWrapper.getConnection();
        List<Action> actions = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Action WHERE `User_id` = ?");
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                actions.add(getActionFromResultSet(rs));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actions;
    }

    private Action getActionFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String description = rs.getString("description");
        Date date = rs.getDate("date");
        int userId = rs.getInt("User_id");
        return (new ActionBuilder())
                .setId(id)
                .setDescription(description)
                .setDate(date)
                .setUserId(userId)
                .build();
    }
}
