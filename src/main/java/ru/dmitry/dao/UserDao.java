package ru.dmitry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitry.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
