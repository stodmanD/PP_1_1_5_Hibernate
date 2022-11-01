package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    //private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS `mydbtest`.`user` (\n" +
        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
        "  `name` VARCHAR(45) NULL,\n" +
        "  `lastName` VARCHAR(45) NULL,\n" +
        "  `age` TINYINT(3) NULL,\n" +
        "  PRIMARY KEY (`id`));";

        Statement statement = null;
            try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            //statement.execute("CREATE TABLE IF NOT EXISTS `mydbtest`.`user` (\n" + "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" + "  `name` VARCHAR(45) NULL,\n" + "  `lastName` VARCHAR(45) NULL,\n" + "  `age` TINYINT(3) NULL,\n" + "  PRIMARY KEY (`id`));");
            System.out.println("Table create");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "drop table IF EXISTS user";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table delete");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user WHERE id=?";
        try {
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User>  getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            System.out.println("Пользователи получены :");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "TRUNCATE TABLE user";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
