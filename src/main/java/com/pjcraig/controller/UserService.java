package com.pjcraig.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;
import com.pjcraig.util.QueryParameterLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles accessing the user RESTful API that is part of the Minecraft JSON Generator application.
 * @author pjcraig
 */
@Path("/users")
public class UserService implements QueryParameterLoader {
    Logger logger = LogManager.getLogger();

    @GET
    @Produces("application/json")
    public Response getUsers(@Context UriInfo uriInfo) {
        Map<String, String> queryParams = getParameterMap(uriInfo.getQueryParameters());

        GenericDao userDao = new GenericDao(User.class);
        List<User> users = new ArrayList<>();

        String parameterId = queryParams.get("id");
        // Verify that id parameter exists
        if (parameterId != null) {
            try {
                int id = Integer.parseInt(parameterId);

                User user = (User) userDao.getById(id);
                // Verify that the user can be accessed
                if (user != null) {
                    users.add(user);
                }
            } catch (NumberFormatException exception) {
                logger.info("Invalid id {} provided when searching for a user.", parameterId);
            } catch (Exception exception) {
                logger.error("Unknown exception occurred while retrieving user by id.", exception);
            }
        } else {
            users = userDao.getAll();
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);

        return Response.status(200).entity(json).build();
    }
}