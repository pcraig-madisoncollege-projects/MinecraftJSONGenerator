package com.pjcraig.persistence;

import com.google.gson.Gson;
import com.pjcraig.entity.MinecraftProfile;
import com.pjcraig.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

public class MinecraftProfileDao implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger();
    public static final String PROPERTIES_PATH = "/minecraftProfiles.properties";
    public static final String PROPERTY_URL = "url";

    private Properties properties;

    /**
     * Constructs a Minecraft profile data access object.
     */
    public MinecraftProfileDao() {
        properties = loadProperties(PROPERTIES_PATH);
    }

    /**
     * Attempts to get an in-game Minecraft profile from a given profile name.
     * @param name The String profile name.
     * @return The Minecraft profile associated with that name or null.
     */
    public MinecraftProfile getProfileByName(String name) {
        MinecraftProfile profile = null;
        if (properties != null) {
            String baseUrl = properties.getProperty(PROPERTY_URL);
            String url = baseUrl + name;
            String response = sendGetRequest(url);
            profile = new Gson().fromJson(response, MinecraftProfile.class);
        }

        return profile;
    }

    /**
     * Sends a GET request to a specified url and returns a JSON response.
     * @param url The String url.
     * @return The String response.
     */
    private String sendGetRequest(String url) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return response;
    }

}
