package com.controller;

import com.dao.UserDaoImplementation;
import com.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        long mobileNumber = Long.parseLong(req.getParameter("mobilenumber"));

        User user = new User(userName,password,mobileNumber);

        new UserDaoImplementation().addUser(user);

        out.println("User Registered Successfully");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
        requestDispatcher.include(req,resp);

    }
}
