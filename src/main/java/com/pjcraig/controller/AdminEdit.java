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
import java.util.List;
import java.util.Locale;

/**
 * This servlet handles admin functionality through POST requests.
 * @author pjcraig
 */
@WebServlet(
        name = "AdminEdit",
        urlPatterns = {"/adminedit"}
)
public class AdminEdit extends HttpServlet {
    private final Logger logger = LogManager.getLogger();

    public static final String PARAMETER_MODE = "mode";
    public static final String PARAMETER_REASON = "reason";
    public static final String PARAMETER_COMMAND_ID = "commandId";
    public static final String PARAMETER_USER_ID = "userId";

    /**
     * Redirects the user to the index page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles admin edit submissions for command unsharing or deleting as well as user removal.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean isAdmin = request.isUserInRole("admin");

        // Verify admin privileges
        if (user != null && isAdmin) {
            String feedback = "Failed to perform requested admin action";

            // Retrieve raw parameters
            String mode = request.getParameter(PARAMETER_MODE);
            String reason = request.getParameter(PARAMETER_REASON);
            String userIdParameter = request.getParameter(PARAMETER_COMMAND_ID);
            String commandIdParameter = request.getParameter(PARAMETER_USER_ID);

            // Verify inputs were received
            if (mode != null && reason != null
                    && userIdParameter != null && commandIdParameter != null) {

                // Attempt to parse id integers
                try {
                    int userId = Integer.parseInt(userIdParameter);
                    int commandId = Integer.parseInt(commandIdParameter);
                    mode = mode.toLowerCase();

                    GenericDao dao;

                    // Perform specific admin functionality based on mode
                    switch (mode) {
                        case "unshare":
                            dao = new GenericDao(Command.class);
                            Command command = (Command) dao.getById(commandId);
                            command.setShared(false);
                            dao.saveOrUpdate(command);
                            feedback = "Successfully unshared the command!";
                            break;
                        default:
                            break;
                    }

                    request.setAttribute("feedback", feedback);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/adminEditResults.jsp");
                    dispatcher.forward(request, response);
                    return;
                } catch (NumberFormatException exception) {
                    logger.info("Unable to parse provided ids for admin editing functionality.", exception);
                } catch (Exception exception) {
                    logger.error("Unknown exception occurred while performing admin functionality.", exception);
                }
            }
        }

        response.sendRedirect("index.jsp");
    }
}
