package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUserTable = "CREATE TABLE IF NOT EXISTS user (" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                "name varchar(50) NOT NULL," +
                "lastname varchar(50) NOT NULL," +
                "age int(3) NOT NULL);";
        try (Statement statement = connection.createStatement()) {
                statement.execute(createUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUserTable = "DROP TABLE IF EXISTS user;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO user (name, lastname, age)" +
                "VALUES ('" + name + "', '" + lastName + "', " + age + ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(saveUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM user WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            statement.execute(removeUserById);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String getAllUsers = "SELECT * FROM user;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE TABLE user;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
