package jm.task.core.jdbc;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        List<User> users = new ArrayList<>();

//        userService.createUsersTable();

//        userService.saveUser("Alex", "Brown", (byte) 24);
//        userService.saveUser("Marsha", "Mellow", (byte) 30);
//        userService.saveUser("Chip", "Munk", (byte) 31);
//        userService.saveUser("Neil", "Down", (byte) 26);

//        users = userService.getAllUsers();

//        for (User user : users) {
//            System.out.println(user);
//        }

//        userService.removeUserById();

//        userService.cleanUsersTable();

//        userService.dropUsersTable();
    }
}
