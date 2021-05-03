package com.pjcraig.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.pjcraig.entity.Command;
import com.pjcraig.entity.User;
import com.pjcraig.persistence.GenericDao;
import com.pjcraig.util.QueryParameterLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
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
@Path("/commands")
public class CommandService implements QueryParameterLoader {
    Logger logger = LogManager.getLogger();

    /**
     * Attempts to GET the specified command using a URL query parameter id.
     * @param uriInfo The URI info containing the id query parameter.
     * @return The JSON representation of the command.
     */
    @GET
    @Produces("application/json")
    public Response getCommands(@Context UriInfo uriInfo) {
        Map<String, String> queryParams = getParameterMap(uriInfo.getQueryParameters());

        GenericDao commandDao = new GenericDao(Command.class);
        List<Command> commands = new ArrayList<>();

        String parameterId = queryParams.get("id");
        // Verify that id parameter exists
        if (parameterId != null) {
            try {
                int id = Integer.parseInt(parameterId);

                Command command = (Command) commandDao.getById(id);
                // Verify that the command can be accessed
                if (command != null && command.isShared()) {
                    commands.add(command);
                }
            } catch (NumberFormatException exception) {
                logger.info("Invalid id {} provided when searching for a command.", parameterId);
            } catch (Exception exception) {
                logger.error("Unknown exception occurred while retrieving command by id.", exception);
            }
        } else {
            commands = commandDao.getByPropertyEqual("shared", true);
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(commands);

        return Response.status(200).entity(json).build();
    }

    /**
     * Attempts to POST the specified command using the provided JSON object.
     * @return The JSON representation of the saved command, or an empty object if failure.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveCommand(@Context HttpServletRequest request, String body) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String json = "{}";

        if (user != null) {
            try {
                logger.info("Received body: {}", body);
                Gson gson = new Gson();
                JsonObject object = gson.fromJson(body, JsonObject.class);

                String raw = object.get("command").getAsString();
                // TODO: Load additional command properties (name, group, date modified, shared, etc.)

                Command command = new Command(user, raw);
                GenericDao<Command> dao = new GenericDao<>(Command.class);
                dao.insert(command);
                session.setAttribute("user", user);

                json = object.getAsString();
            } catch (JsonSyntaxException exception) {
                logger.error("Invalid JSON provided while attempting to save command!");
            } catch (Exception exception) {
                logger.error("Unknown exception occurred while attempting to save command!");
            }
        }

        return Response.ok().entity(json).build();
    }
}