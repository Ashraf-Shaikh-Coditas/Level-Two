package com.dao;

import com.entity.User;
import com.entity.Vehicle;
import com.util.FactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VehicleDaoImplementation implements VehicleDao {

    public List<Vehicle> getVehicles(String vehicleType, int occupancy) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Vehicle where vehicleType = :" +
                " vehicletype and vehicleOccupancy = : vehicleoccupancy and status =: status order by farePerHour");
        query.setParameter("vehicletype",vehicleType);
        query.setParameter("vehicleoccupancy",occupancy);
        query.setParameter("status","available");
        List<Vehicle> vehicleList = query.getResultList();
        return vehicleList;
    }

    public Vehicle getVehicleById(int id) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Vehicle where vehicleId = : id");
        query.setParameter("id", id);
        Vehicle vehicle = (Vehicle) query.getSingleResult();

        return vehicle;
    }

    public void setVehicleStatusAsBooked(Vehicle vehicle) {
        EntityManager entityManager = FactoryProvider.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Vehicle set status =: s where vehicleId = : id");
        query.setParameter("s","booked");
        query.setParameter("id",vehicle.getVehicleId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
