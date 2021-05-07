package com.pjcraig.controller;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.Role;
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
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * This servlet handles requests made to view a user's publicly shared command.
 * @author pjcraig
 */
@WebServlet(
        name = "ViewCommand",
        urlPatterns = {"/command"}
)
public class ViewCommand extends HttpServlet {
    private Logger logger = LogManager.getLogger();

    public static final String URL_UNKNOWN_COMMAND = "/unknownCommand.jsp";
    public static final String URL_VALID_COMMAND = "/viewCommand.jsp";

    /**
     * Redirects the user to the index page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(URL_UNKNOWN_COMMAND);

        String parameterId = request.getParameter("id");
        // Verify that command parameter exists
        if (parameterId != null) {
            // Attempt to load parameter if valid
            try {
                int id = Integer.parseInt(parameterId);

                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                boolean isAdmin = false;
                // Check if user has admin privileges
                if (user != null) {
                    Set<Role> roles = user.getRoles();
                    for (Role role : roles) {
                        if (role.getName().equalsIgnoreCase("admin")) {
                            isAdmin = true;
                            break;
                        }
                    }
                }

                GenericDao dao = new GenericDao(Command.class);
                Command command = (Command) dao.getById(id);

                // Verify command is valid and user has privileges to view it
                if (command != null && (command.isShared() || user.equals(command.getOwner()) || isAdmin)) {
                    User owner = command.getOwner();

                    request.setAttribute("command", command);
                    request.setAttribute("owner", owner.getNickname());
                    request.setAttribute("isAdmin", isAdmin);

                    dispatcher = request.getRequestDispatcher(URL_VALID_COMMAND);
                }
            } catch (NumberFormatException exception) {
                logger.error("Invalid id '{}' entered to view a command!", parameterId);
            } catch (Exception exception) {
                logger.error("Unknown exception occurred.", exception);
            }
        }

        dispatcher.forward(request, response);
    }
}
