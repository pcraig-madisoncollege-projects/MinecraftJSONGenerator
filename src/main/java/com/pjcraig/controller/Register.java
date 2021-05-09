package com.pjcraig.controller;

import com.pjcraig.entity.Role;
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
    public static final String SUCCESS_URL = "/registerSuccess.jsp";
    public static final String FAILURE_URL = "/register.jsp";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_NICKNAME = "nickname";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    public static final int MAX_PASSWORD_LENGTH = 40;

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

        String feedback = null;
        String url = SUCCESS_URL;
        boolean success = true;

        String email = request.getParameter(PARAMETER_EMAIL);
        String nickname = request.getParameter(PARAMETER_NICKNAME);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String confirmPassword = request.getParameter(PARAMETER_CONFIRM_PASSWORD);

        if (email != null && nickname != null
                && password != null && confirmPassword != null) {

            // Remove whitespace characters so that the result can be compared
            String cleanedPassword = password.replaceAll("\\s", "");

            // Verify that password was passed in
            if (password.trim().isEmpty()) {
                feedback = "You must provide a valid password";
                success = false;

            // Password does not match confirmed password
            } else if (!password.equals(confirmPassword)) {
                feedback = "Your passwords do not match.";
                success = false;

            // Password contains whitespace characters
            } else if (!password.equals(cleanedPassword)) {
                feedback = "Your password cannot contain spaces.";
                success = false;

            // Password is too long
            } else if (password.length() > MAX_PASSWORD_LENGTH) {
                feedback = String.format("Your password cannot be longer than %d characters.", MAX_PASSWORD_LENGTH);
                success = false;
            }
        }

        // Verify that no problem occurred before creating new account
        if (success) {
            User user = new User(email, password, nickname);
            Role role = new Role("user", user);
            user.addRole(role);

            dao.insert(user);
        } else {
            url = FAILURE_URL;
        }

        request.setAttribute("feedback", feedback);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
