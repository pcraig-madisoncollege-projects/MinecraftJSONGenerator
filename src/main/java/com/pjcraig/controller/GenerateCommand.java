package com.pjcraig.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet handles requests made to view a user's saved commands.
 * @author pjcraig
 */
@WebServlet(
        name = "GenerateCommand",
        urlPatterns = {"/generate"}
)
public class GenerateCommand extends HttpServlet {
    private Logger logger = LogManager.getLogger();

    /**
     * Redirects the user to the index page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/generate.jsp");

        dispatcher.forward(request, response);
    }
}
