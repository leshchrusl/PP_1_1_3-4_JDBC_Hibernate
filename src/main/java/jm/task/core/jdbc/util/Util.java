package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import jm.task.core.jdbc.model.User;

public class Util {

    private static final String URL = 
            "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection conn;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        return conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close() throws SQLException {
        conn.close();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(Environment.DRIVER,
                        "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,
                        "jdbc:mysql://localhost:3306/test");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT,
                        "org.hibernate.dialect.MySQL8Dialect");


                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment
                        .CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                                .build();

                sessionFactory = configuration
                        .buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.err.println("Ошибка соединения через Hibernate : " +
                        e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
