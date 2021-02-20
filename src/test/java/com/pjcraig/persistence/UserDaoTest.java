package com.pjcraig.persistence;

import com.pjcraig.entity.User;
import com.pjcraig.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    GenericDao dao;

    /**
     * Initializes the dao and testing database before any test is run.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies retrieving users by id from database.
     */
    @Test
    void getByIdSuccess() {
        User expectedUser = new User("jdoe@example.com", "password1234", "Kat");
        expectedUser.setId(2);
        User retrievedUser = (User) dao.getById(2);
        assertNotNull(retrievedUser);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verifies success of updating users in database.
     */
    @Test
    void saveOrUpdateSuccess() {
        int id = 3;
        User userToUpdate = (User) dao.getById(id);
        String newNickname = "supersecretstuff123";
        String initialNickname = userToUpdate.getNickname();
        userToUpdate.setNickname(newNickname);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User) dao.getById(id);
        assertNotEquals(initialNickname, retrievedUser.getNickname());
        assertEquals(newNickname, retrievedUser.getNickname());
    }

    /**
     * Verifies success of user insertion into the database.
     */
    @Test
    void insertSuccess() {
        User newUser = new User("jeff@example.com", "longpassword123", "Jeffster");
        int id = dao.insert(newUser);
        newUser.setId(id);
        assertNotEquals(1, id);
        User insertedUser = (User) dao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verifies success of user deletion from the database.
     */
    @Test
    void deleteSuccess() {
        int id = 2;
        User user = (User) dao.getById(id);
        assertNotNull(user);
        dao.delete(user);
        assertNull(dao.getById(id));
    }

    /**
     * Verifies successful retrieval of all users from the database.
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }
}