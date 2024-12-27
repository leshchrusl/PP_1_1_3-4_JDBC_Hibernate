package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id int auto_increment primary key," +
                "name VARCHAR(45) not null," +
                "lastName VARCHAR(45) not null," +
                "age int not null);";

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка при создании " +
                    "таблицы: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка при удалении " +
                    "таблицы: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory()
                .openSession()) {
             transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User с именем — " + name +
                    " добавлен в базу данных");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка сохранения пользователя: " +
                    e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка при удалении " +
                    "пользователя по id: " +
                    e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();

        try (Session session = Util.getSessionFactory()
                .openSession()) {

            users = session.createQuery("from User",
                    User.class).list();

        } catch (Exception e) {
            System.err.println("Ошибка при получении списка " +
                    "пользователей из таблицы" +
                    e.getMessage());
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "DELETE FROM users";

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка при удалении всех " +
                    "пользователей из таблицы: "
                    + e.getMessage());
        }

    }
}
