package com.dao;

import com.entity.Booking;
import com.util.FactoryProvider;

import javax.persistence.EntityManager;

public class BookingDaoImplementation implements BookingDao {

    public void addBooking(Booking booking) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(booking);
        entityManager.getTransaction().commit();
    }
}
