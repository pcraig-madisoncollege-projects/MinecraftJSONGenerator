package com.pjcraig.persistence;

import com.pjcraig.entity.Command;
import com.pjcraig.entity.User;
import com.pjcraig.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Command expectedCommand = new Command("/tellraw @a [{\"text\":\"This is a test\",\"color\":\"green\"}]",
                owner, true);
        expectedCommand.setId(2);
        Command retrievedCommand = (Command) commandDao.getById(2);
        assertNotNull(retrievedCommand);
        assertEquals(expectedCommand, retrievedCommand);
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
        Command newCommand = new Command("/tellraw @a [\"Why, hello there!\"]",
                owner, true);
        int id = commandDao.insert(newCommand);
        newCommand.setId(id);
        assertNotEquals(1, id);
        Command insertedCommand = (Command) commandDao.getById(id);
        assertEquals(newCommand, insertedCommand);
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