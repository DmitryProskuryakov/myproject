package ru.dmitry.dao;
import ru.dmitry.model.User;
import java.util.List;

public interface UserDAOInterface{
    List<User> getAllUsers();

    User getUser(int id);

    void addUser(User user);

    void createTable();

    void dropUsersTable();

    void removeUserBtId(int id);

    void cleanUsersTable();

    void changeUser(int id, User user);
}
