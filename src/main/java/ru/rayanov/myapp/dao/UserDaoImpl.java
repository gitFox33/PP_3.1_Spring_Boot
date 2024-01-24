package ru.rayanov.myapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rayanov.myapp.models.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(Long id) {

        return em.find(User.class, id);

    }

    @Override
    public void editUser(Long id, User user) {
        User userToBeEdit = getUserById(id);
        userToBeEdit.setName(user.getName());
        userToBeEdit.setEmail(user.getEmail());
        userToBeEdit.setAge(user.getAge());
        em.merge(userToBeEdit);
    }

    @Override
    public void deleteUser(Long id) {
        em.remove(getUserById(id));

    }

}