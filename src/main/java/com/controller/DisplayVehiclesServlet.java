package com.controller;

import com.dao.VehicleDaoImplementation;
import com.entity.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/DisplayVehicles")
public class DisplayVehiclesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String vehicleType = req.getParameter("vehicletype");
        int occupancy = Integer.parseInt(req.getParameter("occupancytype"));

        List<Vehicle> vehicleList = new VehicleDaoImplementation().getVehicles(vehicleType,occupancy);

        out.println("<center>\n" +
                "   <table border=\"2px solid\">\n" +
                "     <tr><th>Vehicle Id</th><th>Model Name</th><th>Registration Number</th><th>Vehicle Type</th>\n" +
                "       <th>Vehicle Occupancy</th><th>Fare Per Hour</th><th>Vehicle Status</th>" +
                "<th></th>\n" +
                "     </tr>");

        out.println("<form action=\"Booking\" method=\"get\">\n");

        for (Vehicle vehicle : vehicleList) {
            out.println("<tr><th>" + vehicle.getVehicleId() + "</th><th>" + vehicle.getVehicleName() + "</th><th>" + vehicle.getRegistrationNumber() + "</th>" +
                    "<th>" + vehicle.getVehicleType() + "</th>\n" +
                    "       <th>" + vehicle.getVehicleOccupancy() + "</th><th>" + vehicle.getFarePerHour() + "</th>" +
                    "<th>" + vehicle.getStatus() + "</th>" +
                    "<th><input type=\"radio\" name='vehicleId' value = " + vehicle.getVehicleId() + " ></th>\n" +
                    "     </tr></center>");
        }

        out.println("Welcome User<br>" +
                "     Select Starting time :\n" +
                "     <input type=datetime-local name=\"starttime\"><hr>\n" +
                "\n" +
                "     Select Ending Here :\n" +
                "     <input type=datetime-local name=\"endtime\"><hr>\n" +
                "\n" +
                "     <input type=\"submit\" value=\"SUBMIT\"><input type=\"reset\" value=\"RESET\">\n" +
                "\n" +
                "   </form>");


    }
}
