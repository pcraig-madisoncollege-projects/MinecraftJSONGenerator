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
 * This servlet handles requests made to navigate to the generate command page with or without a loaded command.
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
        String parameterId = request.getParameter("id");
        // Verify that command parameter exists
        if (parameterId != null) {
            // Attempt to load parameter if valid
            try {
                int id = Integer.parseInt(parameterId);

                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                GenericDao dao = new GenericDao(Command.class);
                Command command = (Command) dao.getById(id);

                // Verify that a valid command exists and is publicly visible or the user's own command
                if (command != null && (user.equals(command.getOwner()))) {
                    request.setAttribute("command", command);
                }
            } catch (NumberFormatException exception) {
                logger.error("Invalid id '{}' entered to edit a command!", parameterId);
            } catch (Exception exception) {
                logger.error("Unknown exception occurred while attempting to edit command.", exception);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/generate.jsp");
        dispatcher.forward(request, response);
    }
}
