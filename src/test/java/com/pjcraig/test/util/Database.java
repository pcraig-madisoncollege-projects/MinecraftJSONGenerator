package com.pjcraig.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Provides access the database
 * Created on 8/31/16.
 *
 * @author pwaite
 * @author pjcraig
 */
public class Database {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    /**
     * Instantiates a single Database instance using defined properties.
     */
    private Database() {
        loadProperties();
    }

    /**
     * Retrieves the properties associated with this database connection.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException exception) {
            logger.error("Error! Failed to load database properties!" , exception);
        } catch (Exception exception) {
            logger.error("Error! Unknown exception occurred while loading database properties.", exception);
        }

    }

    /**
     * Gets the database instance.
     * @return The database.
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets the connection to the database.
     * @return The Connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Attempts to connect the Database object to the physical database system.
     * @throws Exception Whether or not an exception occurs while connecting.
     */
    public void connect() throws Exception {
        if (connection != null)
            return;
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    /**
     * Attempts to close the connection to the database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                logger.error("Error! Unable to close database connection.", exception);
            }
        }

        connection = null;
    }

    /**
     * Executes an external sql file.
     *
     * @param sqlFile The path to the sql file to be run.
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(sqlFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect();
            stmt = connection.createStatement();

            while (true) {
                String sql = br.readLine();
                if (sql == null) {
                    break;
                }
                stmt.executeUpdate(sql);

            }

        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            disconnect();
        }
    }
}