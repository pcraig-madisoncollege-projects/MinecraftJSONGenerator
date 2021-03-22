package com.pjcraig.persistence;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.User;
import com.pjcraig.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandDaoTest {

    GenericDao commandDao;
    GenericDao userDao;

    /**
     * Initializes the DAOs and testing database before any test is run.
     */
    @BeforeEach
    void setUp() {
        commandDao = new GenericDao(Command.class);
        userDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies retrieving commands by id from database.
     */
    @Test
    void getByIdSuccess() {
        User owner = (User) userDao.getById(3);
        Command expectedCommand = new Command(owner,
                "/tellraw @a [{\"text\":\"This is a test\",\"color\":\"green\"}]");
        expectedCommand.setId(2);
        Command retrievedCommand = (Command) commandDao.getById(2);
        assertNotNull(retrievedCommand);
        assertEquals(expectedCommand, retrievedCommand);
    }

    /**
     * Verifies retrieving command by a given property from database succeeds.
     */
    @Test
    void getByPropertyEqualsSuccess() {
        Command expectedCommand = (Command) commandDao.getById(2);

        List<Command> commands = commandDao.getByPropertyEqual("shared", expectedCommand.isShared());

        assertEquals(1, commands.size());
        Command actualCommand = commands.get(0);
        assertEquals(expectedCommand, actualCommand);
    }

    /**
     * Verifies success of updating commands in database.
     */
    @Test
    void saveOrUpdateSuccess() {
        int id = 3;
        Command commandToUpdate = (Command) commandDao.getById(id);
        String newCommand = "/tellraw @a [\"Hello!\"]";
        String initialCommand = commandToUpdate.getCommand();
        commandToUpdate.setCommand(newCommand);
        commandDao.saveOrUpdate(commandToUpdate);
        Command retrievedCommand = (Command) commandDao.getById(id);
        assertNotEquals(initialCommand, retrievedCommand.getCommand());
        assertEquals(newCommand, retrievedCommand.getCommand());
    }

    /**
     * Verifies success of command insertion into the database.
     */
    @Test
    void insertSuccess() {
        User owner = (User) userDao.getById(2);
        Command newCommand = new Command(owner, "/tellraw @a [\"Why, hello there!\"]");
        owner.addCommand(newCommand);

        int id = commandDao.insert(newCommand);
        newCommand.setId(id);

        assertNotEquals(1, id);
        Command insertedCommand = (Command) commandDao.getById(id);

        assertEquals(newCommand, insertedCommand);
        assertNotNull(insertedCommand.getOwner());
        assertEquals(owner, insertedCommand.getOwner());
    }

    /**
     * Verifies success of command deletion from the database.
     */
    @Test
    void deleteSuccess() {
        int id = 2;
        Command command = (Command) commandDao.getById(id);
        assertNotNull(command);
        commandDao.delete(command);
        assertNull(commandDao.getById(id));
    }

    /**
     * Verifies successful retrieval of all commands from the database.
     */
    @Test
    void getAllSuccess() {
        List<Command> commands = commandDao.getAll();
        assertEquals(3, commands.size());
    }
}