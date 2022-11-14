package com.dao;

import com.entity.User;
import com.entity.Vehicle;
import com.util.FactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VehicleDaoImplementation implements VehicleDao{

    public List<Vehicle> getVehicles() {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Vehicle order by farePerHour");
        List<Vehicle> vehicleList = query.getResultList();
        return vehicleList;
    }

    public Vehicle getVehicleById(int id) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Vehicle where vehicleId = : id");
        query.setParameter("id",id);
        Vehicle vehicle = (Vehicle) query.getSingleResult();

        return vehicle;
    }
}
