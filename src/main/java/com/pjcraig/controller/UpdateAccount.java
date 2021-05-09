package com.pjcraig.controller;

import com.pjcraig.entity.MinecraftProfile;
import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;
import com.pjcraig.persistence.MinecraftProfileDao;
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
 * This servlet handles requests made for editing a user's account information.
 * @author pjcraig
 */
@WebServlet(
        name = "UpdateAccount",
        urlPatterns = {"/account"}
)
public class UpdateAccount extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public static final String FORWARD_URL = "/account.jsp";
    public static final String PARAMETER_NICKNAME = "nickname";
    public static final String PARAMETER_MC_PROFILE = "minecraftProfile";
    public static final String PARAMETER_OLD_PASSWORD = "oldPassword";
    public static final String PARAMETER_NEW_PASSWORD = "newPassword";
    public static final int MAX_PASSWORD_LENGTH = 40;

    /**
     * Forwards the user to the user account page.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_URL);
        dispatcher.forward(request, response);
    }

    /**
     * Handles user account updating submission and interacts with the database.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException Whether or not the servlet encounters an error.
     * @throws IOException Whether or not an IO exception occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {

            String feedback = "Successfully updated your profile!";
            boolean success = true;
            GenericDao dao = new GenericDao(User.class);

            String nickname = request.getParameter(PARAMETER_NICKNAME);
            String minecraftProfile = request.getParameter(PARAMETER_MC_PROFILE);
            String oldPassword = request.getParameter(PARAMETER_OLD_PASSWORD);
            String newPassword = request.getParameter(PARAMETER_NEW_PASSWORD);

            // Update nickname if provided
            if (nickname != null) {
                if (nickname.trim().isEmpty()) {
                    feedback = "You must enter a valid nickname";
                    success = false;
                } else {
                    user.setNickname(nickname);
                }
            }

            // Update Minecraft Profile if provided
            if (minecraftProfile != null) {

                // Unlink account if no Minecraft profile is specified
                if (minecraftProfile.trim().isEmpty()) {
                    user.setMinecraftProfile(null);
                } else {
                    MinecraftProfileDao mcDao = new MinecraftProfileDao();
                    MinecraftProfile profile = mcDao.getProfileByName(minecraftProfile);

                    // Verify that specified profile exists
                    if (profile != null) {
                        user.setMinecraftProfile(minecraftProfile);
                    } else {
                        feedback = "You must enter a valid Minecraft profile name.";
                        success = false;
                    }
                }
            }

            // Update account password if provided
            if (oldPassword != null && newPassword != null
                    && (!oldPassword.trim().isEmpty() || !newPassword.trim().isEmpty())) {
                String cleanedPassword = newPassword.replaceAll("\\s", "");

                // No new password entered
                if (newPassword.trim().isEmpty()) {
                    feedback = "You must enter a valid new password.";
                    success = false;

                // Old password equals new password
                } else if (!oldPassword.equals(user.getPassword())) {
                    feedback = "The old password does not match the password associated with this account.";
                    success = false;

                // Old password is the same as new password
                } else if (oldPassword.equals(cleanedPassword)) {
                    feedback = "Your new password cannot match your old password.";
                    success = false;

                // New password contains whitespace character(s)
                } else if (!newPassword.equals(cleanedPassword)) {
                    feedback = "Spaces are not allowed in your password.";
                    success = false;

                // New password is too long
                } else if (newPassword.length() > MAX_PASSWORD_LENGTH) {
                    feedback = String.format("Your password cannot be longer than %d characters.", MAX_PASSWORD_LENGTH);
                    success = false;
                } else {
                    user.setPassword(newPassword);
                }
            }

            // Update account if no errors occurred
            if (success) {
                dao.saveOrUpdate(user);
            }

            request.setAttribute("success", success);
            request.setAttribute("feedback", feedback);
            RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_URL);
            dispatcher.forward(request, response);
            return;
        }

        response.sendRedirect("index.jsp");
    }
}
