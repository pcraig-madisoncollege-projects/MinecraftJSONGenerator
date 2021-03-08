package com.pjcraig.persistence;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.Role;
import com.pjcraig.entity.User;
import com.pjcraig.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    GenericDao userDao;
    GenericDao commandDao;
    GenericDao roleDao;

    /**
     * Initializes the DAOs and testing database before any test is run.
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        commandDao = new GenericDao(Command.class);
        roleDao = new GenericDao(Role.class);

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
        User retrievedUser = (User) userDao.getById(2);
        assertNotNull(retrievedUser);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * Verifies retrieving users by a given property from database succeeds.
     */
    @Test
    void getByPropertyEqualsSuccess() {
        User expectedUser = (User) userDao.getById(1);

        List<User> users = userDao.getByPropertyEqual("nickname", expectedUser.getNickname());

        assertEquals(1, users.size());
        User actualUser = users.get(0);
        assertEquals(expectedUser, actualUser);
    }

    /**
     * Verifies that user command removal updates in database.
     */
    @Test
    void removeCommandSuccess() {
        int id = 2;
        User initialUser = (User) userDao.getById(id);
        Command commandToBeRemoved = new Command("/title @a title [{\"text\":\"Hello!\"}]", initialUser);
        commandToBeRemoved.setId(3);

        assertEquals(1, initialUser.getCommands().size());
        initialUser.removeCommand(commandToBeRemoved);
        userDao.saveOrUpdate(initialUser);

        User retrievedUser = (User) userDao.getById(id);
        assertEquals(0, retrievedUser.getCommands().size());
    }

    /**
     * Verifies that user command addition updates in database.
     */
    @Test
    void addCommandSuccess() {
        int id = 2;
        User initialUser = (User) userDao.getById(id);
        Command commandToBeAdded = new Command("/tellraw @a [\"This is a new command\"]",
                initialUser);

        assertEquals(1, initialUser.getCommands().size());
        initialUser.addCommand(commandToBeAdded);
        userDao.saveOrUpdate(initialUser);

        User retrievedUser = (User) userDao.getById(id);
        assertEquals(2, retrievedUser.getCommands().size());
    }

    /**
     * Verifies that user role removal updates in database.
     */
    @Test
    void removeRoleSuccess() {
        int id = 1;
        User initialUser = (User) userDao.getById(id);
        Role roleToBeRemoved = new Role("admin", initialUser);
        roleToBeRemoved.setId(1);

        assertEquals(1, initialUser.getRoles().size());
        initialUser.removeRole(roleToBeRemoved);
        userDao.saveOrUpdate(initialUser);

        User retrievedUser = (User) userDao.getById(id);
        assertEquals(0, retrievedUser.getRoles().size());
    }

    /**
     * Verifies that user role addition updates in database.
     */
    @Test
    void addRoleSuccess() {
        int id = 2;
        User initialUser = (User) userDao.getById(id);
        Role roleToBeAdded = new Role("admin", initialUser);

        assertEquals(0, initialUser.getRoles().size());
        initialUser.addRole(roleToBeAdded);
        userDao.saveOrUpdate(initialUser);

        User retrievedUser = (User) userDao.getById(id);
        assertEquals(1, retrievedUser.getRoles().size());
    }

    /**
     * Verifies success of updating users in database.
     */
    @Test
    void saveOrUpdateSuccess() {
        int id = 3;
        User userToUpdate = (User) userDao.getById(id);
        String newNickname = "supersecretstuff123";
        String initialNickname = userToUpdate.getNickname();
        userToUpdate.setNickname(newNickname);
        userDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User) userDao.getById(id);
        assertNotEquals(initialNickname, retrievedUser.getNickname());
        assertEquals(newNickname, retrievedUser.getNickname());
    }

    /**
     * Verifies success of user insertion into the database.
     */
    @Test
    void insertSuccess() {
        User newUser = new User("jeff@example.com", "longpassword123", "Jeffster");
        int id = userDao.insert(newUser);
        newUser.setId(id);
        assertNotEquals(1, id);
        User insertedUser = (User) userDao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verifies success of user and command insertion into the database.
     */
    @Test
    void insertWithCommandSuccess() {
        User newUser = new User("jeff@example.com", "longpassword123", "Jeffster");

        String command = "/title @a [\"This is a new command!\"]";
        Command commandEntity = new Command(command, newUser);
        newUser.addCommand(commandEntity);

        int id = userDao.insert(newUser);
        newUser.setId(id);

        assertNotEquals(1, id);
        User insertedUser = (User) userDao.getById(id);
        assertEquals(newUser, insertedUser);
        assertEquals(1, insertedUser.getCommands().size());
    }

    /**
     * Verifies success of user deletion along with that user's commands from the database.
     */
    @Test
    void deleteSuccess() {
        int id = 2;
        User user = (User) userDao.getById(id);
        assertNotNull(user);
        List<Command> commands = commandDao.getByPropertyEqual("owner", user);
        assertEquals(1, commands.size());

        userDao.delete(user);

        commands = commandDao.getByPropertyEqual("owner", user);
        assertNull(userDao.getById(id));
        assertEquals(0, commands.size());
    }

    /**
     * Verifies successful retrieval of all users from the database.
     */
    @Test
    void getAllSuccess() {
        List<User> users = userDao.getAll();
        assertEquals(3, users.size());
    }
}