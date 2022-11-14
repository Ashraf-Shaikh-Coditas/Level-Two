package com.controller;

import com.dao.BookingDaoImplementation;
import com.dao.UserDaoImplementation;
import com.dao.VehicleDaoImplementation;
import com.entity.Booking;
import com.entity.User;
import com.entity.Vehicle;
import javafx.util.Duration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        ServletContext servletContext = req.getServletContext();
        int userId = Integer.parseInt(servletContext.getAttribute("userId").toString());
        int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
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

        long timeDifference = endl - startl;  // For checking time constraint i.e
        // it should be greater than one hour
        System.out.println(timeDifference);


        if ((startl < endl) && (timeDifference >= 3600000)) { // This covers scenario of equal time, invalid time
            User user = new UserDaoImplementation().getUserById(userId);
            Vehicle vehicle = new VehicleDaoImplementation().getVehicleById(vehicleId);

            double numberofHours = timeDifference / 3600000;
            double totalFare = numberofHours * vehicle.getFarePerHour();

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setVehicle(vehicle);
            booking.setPickUpTime(startl);
            booking.setReturnTime(endl);
            booking.setTotalFare((float) totalFare);


            new BookingDaoImplementation().addBooking(booking);
            new VehicleDaoImplementation().setVehicleStatusAsBooked(vehicle);
            out.println("Booking Added Successfully");

            out.println("<center>\n" +
                    "   <table border=\"2px solid\">\n" +
                    "     <tr><th>User Name</th><th>Model Name</th><th>Registration Number</th><th>Pickup Time</th>\n" +
                    "       <th>Return Time</th><th>Total Fare</th>" +
                    "     </tr>");
            out.println("<tr><th>" + user.getUserName() + "</th><th>" + vehicle.getVehicleName() + "</th>" +
                    "<th>" + vehicle.getRegistrationNumber() + "</th><th>" + startDate + "</th>\n" +
                    "       <th>" + endtime + "</th><th>" + totalFare + "</th>" +
                    "     </tr></table></center>");
            out.println("<center><h2><a href='Login.jsp'>Back to Login</a></h2></center>");


        } else {
            out.println("<h1>Time not valid. Try Again</h1>");

        }


    }
}
