package com.pjcraig.controller;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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
        User user = (User) getServletContext().getAttribute("user");

        if (user != null) {
            Set<Command> commands = user.getCommands();

            request.setAttribute("commands", commands);

            response.sendRedirect("commands.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
