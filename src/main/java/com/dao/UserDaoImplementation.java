package com.dao;

import com.entity.User;
import com.util.FactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImplementation implements UserDao {

    public void addUser(User user) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public User getUserByName(String username) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from User where userName = : username");
        query.setParameter("username",username);
        User user = (User) query.getSingleResult();

        return user;
    }

    public User getUserById(int id) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from User where userId = : id");
        query.setParameter("id",id);
        User user = (User) query.getSingleResult();

        return user;
    }
}
