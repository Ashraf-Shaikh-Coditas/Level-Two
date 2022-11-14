package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String password;
    private long mobileNumber;
    private String status;

    @OneToMany(mappedBy = "user")
    List<Booking> bookingList;

    public User(String userName, String password, long mobileNumber, String status, List<Booking> bookingList) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.status = status;
        this.bookingList = bookingList;
    }

    public User(String userName, String password, long mobileNumber) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.status = "active";
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
