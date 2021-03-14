package com.pjcraig.persistence;

import com.pjcraig.entity.MinecraftProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Minecraft profile dao for correct account validation.
 * @author pjcraig
 */
public class MinecraftProfileDaoTest {
    MinecraftProfileDao dao;

    @BeforeEach
    void setUp() {
        dao = new MinecraftProfileDao();
    }

    /**
     * Tests a valid Minecraft profile against the Mojang API profile lookup utility.
     */
    @Test
    void getValidProfileSuccess() {
        String profileName = "Notch"; // The creator of Minecraft
        String uuid = "069a79f444e94726a5befca90e38aaf5";
        MinecraftProfile expectedProfile = new MinecraftProfile(profileName, uuid);

        MinecraftProfile actualProfile = dao.getProfileByName(profileName);

        assertEquals(expectedProfile, actualProfile);
    }

    /**
     * Tests an invalid Minecraft profile against the Mojang API profile lookup utility.
     */
    @Test
    void getInvalidProfileSuccess() {
        String profileName = "user!"; // Invalid profile due to exclamation mark

        MinecraftProfile profile = dao.getProfileByName(profileName);

        assertNull(profile);
    }

    /**
     * Tests an invalid Minecraft profile that has too long of a name against
     * the Mojang API profile lookup utility.
     */
    @Test
    void getInvalidProfileNameLengthSuccess() {
        String profileName = "ReallyLongUsername"; // Invalid profile due to length (between 3-16 accepted)

        MinecraftProfile profile = dao.getProfileByName(profileName);

        assertNull(profile);
    }
}
