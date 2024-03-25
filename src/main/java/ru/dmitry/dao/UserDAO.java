package ru.dmitry.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.dmitry.model.User;
import ru.dmitry.util.Connect;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements UserDAOInterface{
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            listUsers = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public User getUser(int id) {
        List<User> listUsers = new ArrayList<>();
        User user = null;
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            listUsers = session.createQuery("from User", User.class).getResultList();
            user = listUsers.stream().filter(el-> el.getId() == id).findAny().orElse(null);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT KEY AUTO_INCREMENT, name VARCHAR(30), surname VARCHAR(30), age INT)").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }

    @Override
    public void removeUserBtId(int id) {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }

    @Override
    public void cleanUsersTable() {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }
    @Override
    public void changeUser(int id, User user) {
        entityManager = Connect.getEntityManager();
        try (Session session = entityManager.unwrap(Session.class)) {
            session.beginTransaction();
            String hql = "UPDATE User SET name= :paramName, surname= :paramSurname, age= :paramAge WHERE id = :paramId";
            session.createQuery(hql).setParameter("paramName", user.getName()).
            setParameter("paramSurname", user.getSurname()).
            setParameter("paramAge", user.getAge()).
            setParameter("paramId", id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException h) {
            h.printStackTrace();
        }
    }
}
