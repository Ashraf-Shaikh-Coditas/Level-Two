package com.controller;

import com.dao.BookingDaoImplementation;
import com.dao.UserDaoImplementation;
import com.dao.VehicleDaoImplementation;
import com.entity.Booking;
import com.entity.User;
import com.entity.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("Booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int userId = Integer.parseInt(req.getParameter("uid"));
        int vehicleId = Integer.parseInt(req.getParameter("vid"));

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss");
            Date startDate;
            Date endDate;
        try {
            startDate = (Date) simpleDateFormat.parse(starttime);
            endDate = (Date) simpleDateFormat.parse(endtime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long startl = startDate.getTime();
        long endl = endDate.getTime();

        if(startl!=endl) {
            User user = new UserDaoImplementation().getUserById(userId);
            Vehicle vehicle = new VehicleDaoImplementation().getVehicleById(vehicleId);


            Booking booking = new Booking();
            booking.setUser(user);
            booking.setVehicle(vehicle);
            booking.setPickUpTime(startl);
            booking.setReturnTime(endl);

            new BookingDaoImplementation().addBooking(booking);
        } else {
            out.println("Time not valid");
        }




    }
}
