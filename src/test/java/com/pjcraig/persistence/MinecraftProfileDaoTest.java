package com.pjcraig.persistence;

import com.pjcraig.entity.MinecraftProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the Minecraft profile dao for correct account validation.
 * @author pjcraig
 */
public class MinecraftProfileDaoTest {
    Gson mapper;

    @BeforeEach
    void setUp() {
        mapper = new Gson();
    }

    /**
     * Tests a valid Minecraft profile against the Mojang API profile lookup utility.
     */
    @Test
    void getValidProfileSuccess() {
        String profileName = "Notch"; // The creator of Minecraft
        String uuid = "069a79f444e94726a5befca90e38aaf5";
        MinecraftProfile expectedProfile = new MinecraftProfile(profileName, uuid);

        String url = String.format("https://api.mojang.com/users/profiles/minecraft/%s", profileName);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        MinecraftProfile actualProfile = mapper.fromJson(response, MinecraftProfile.class);

        assertEquals(expectedProfile, actualProfile);
    }
}
