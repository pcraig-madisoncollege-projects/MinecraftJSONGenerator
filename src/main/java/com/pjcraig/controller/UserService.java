package com.pjcraig.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This class handles accessing the user RESTful API that is part of the Minecraft JSON Generator application.
 * @author pjcraig
 */
@Path("/users")
public class UserService {
    @GET
    @Produces("text/plain")
    public Response getUsers() {
        String output = "Hello World"; // demo output
        return Response.status(200).entity(output).build();
    }
}