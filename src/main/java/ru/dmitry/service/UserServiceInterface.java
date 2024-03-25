package ru.dmitry.service;

import ru.dmitry.model.User;

public interface UserServiceInterface {
    User getUser(int id);

    void addUser(User user);

    void createTable();

    void dropUsersTable();

    void removeUserById(int id);

    void cleanUsersTable();
    void changeUser(int id, User user);
}
