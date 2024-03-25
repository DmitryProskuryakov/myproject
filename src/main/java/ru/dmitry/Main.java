package ru.dmitry;

import ru.dmitry.dao.UserDAO;
import ru.dmitry.model.User;
import ru.dmitry.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createTable();
        User user1 = new User("Dima", "Proskuryakov", 25);
        User user2 = new User("Olya", "Popov", 22);
        User user3 = new User("Sasha", "Petrov", 30);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
//        userService.removeUserById(3);
//        userService.dropUsersTable();
    }
}
