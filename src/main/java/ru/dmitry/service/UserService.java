package ru.dmitry.service;

import org.springframework.stereotype.Component;
import ru.dmitry.dao.UserDAO;
import ru.dmitry.model.User;


import java.util.List;

@Component
public class UserService implements UserServiceInterface{
    private UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void createTable() {
        userDAO.createTable();
    }

    @Override
    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    @Override
    public void removeUserById(int id) {
        userDAO.removeUserBtId(id);
    }

    @Override
    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
    @Override
    public void changeUser(int id, User user) {
        userDAO.changeUser(id, user);
    }
}
