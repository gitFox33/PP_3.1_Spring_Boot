package ru.rayanov.myapp.dao;

import ru.rayanov.myapp.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(Long id);

    void editUser(Long id, User user);

    void deleteUser(Long id);

}