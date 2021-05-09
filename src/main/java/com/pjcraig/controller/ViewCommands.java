package com.pjcraig.controller;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet handles requests made to view a user's saved commands.
 * @author pjcraig
 */
@WebServlet(
        name = "ViewCommands",
        urlPatterns = {"/commands"}
)
public class ViewCommands extends HttpServlet {
    private final Logger logger = LogManager.getLogger();

    /**
     * Redirects the user to the index page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve old user from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Verify that user is logged in
        if (user != null) {
            // Refresh session user (in case saved command data has changed)
            GenericDao<User> dao = new GenericDao<>(User.class);
            user = dao.getById(user.getId());
            session.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/commands.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
