package com.entity;

import com.util.FactoryProvider;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User();
        user.setUserName("Ashraf");
        user.setPassword("Ashraf@123");
        user.setMobileNumber(9975398919L);
        user.setStatus("ACTIVE");
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
