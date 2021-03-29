package com.pjcraig.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pjcraig.entity.Command;
import com.pjcraig.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class handles accessing the user RESTful API that is part of the Minecraft JSON Generator application.
 * @author pjcraig
 */
@Path("/users")
public class UserService {
    Logger logger = LogManager.getLogger();

    @GET
    @Produces("application/json")
    public Response getUsers() {
        GenericDao commandDao = new GenericDao(Command.class);
        List<Command> commands = commandDao.getByPropertyEqual("shared", true);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(commands);

        return Response.status(200).entity(json).build();
    }
}