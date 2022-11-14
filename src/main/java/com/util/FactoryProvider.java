package com.util;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryProvider {
    public static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getFactory() {
        if(entityManagerFactory==null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("ashraf");

        }
        return entityManagerFactory;
    }


}
