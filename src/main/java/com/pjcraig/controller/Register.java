package com.pjcraig.controller;

import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * This servlet handles requests made for the user registration page.
 * @author pjcraig
 */
@WebServlet(
        name = "Register",
        urlPatterns = {"/register"}
)
public class Register extends HttpServlet {
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_NICKNAME = "nickname";
    public static final String PARAMETER_PASSWORD = "password";

    /**
     * Forwards the user to the user registration page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles user registration submission and interacts with the database.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao dao = new GenericDao(User.class);

        // TODO: Implement input processing for safety and display feedback to users
        String email = request.getParameter(PARAMETER_EMAIL);
        String nickname = request.getParameter(PARAMETER_NICKNAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        User user = new User(email, password, nickname);

        dao.insert(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }
}
