package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import static jm.task.core.jdbc.util.Util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {

         String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id int auto_increment primary key," +
                "name VARCHAR(45) not null," +
                "lastName VARCHAR(45) not null," +
                "age int not null);";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {

            System.err.println("Ошибка при создании таблицы: "
            + e.getMessage());

        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {

            System.err.println("Ошибка при удалении таблицы: "
                    + e.getMessage());

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users(name, lastName, age)" +
                " VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);

            ps.executeUpdate();

            System.out.println("User с именем — " + name +
                    " добавлен в базу данных");

        } catch (SQLException e) {

            System.err.println("Ошибка при добавлении " +
                    "пользователя в таблицу: "
                    + e.getMessage());

        }
    }

    @Override
    public void removeUserById(long id) {

        String sql = "DELETE FROM users where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Ошибка при удалении пользователя" +
                    " по id: " + e.getMessage());

        }
    }

    @Override
    public List<User> getAllUsers() {

        User user = null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {

                user = new User();

                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));

                System.out.println("Получены данные пользователя: \n" + user);

                users.add(user);
            }

        } catch (SQLException e) {

            System.err.println("Ошибка при получении всех пользователей " +
                    "из таблицы: " + e.getMessage());

        }

        return users;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "DELETE FROM users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {

            System.err.println("Ошибка при очистке таблицы :" +
                    e.getMessage());

        }
    }
}
