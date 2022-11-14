package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private String vehicleType;
    private int vehicleOccupancy;
    private float farePerHour;

    private String status;

    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookingList;

    public Vehicle(String vehicleName, String registrationNumber, String vehicleType, int vehicleOccupancy, float farePerHour, List<Booking> bookingList) {
        this.vehicleName = vehicleName;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.vehicleOccupancy = vehicleOccupancy;
        this.farePerHour = farePerHour;
        this.bookingList = bookingList;
    }



    public Vehicle(String vehicleName, String registrationNumber, String vehicleType, int vehicleOccupancy, float farePerHour) {
        this.vehicleName = vehicleName;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.vehicleOccupancy = vehicleOccupancy;
        this.farePerHour = farePerHour;
        this.status = "available";
    }

    public Vehicle() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleOccupancy() {
        return vehicleOccupancy;
    }

    public void setVehicleOccupancy(int vehicleOccupancy) {
        this.vehicleOccupancy = vehicleOccupancy;
    }

    public float getFarePerHour() {
        return farePerHour;
    }

    public void setFarePerHour(float farePerHour) {
        this.farePerHour = farePerHour;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
