package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    private static final Logger logger = Logger.getLogger(ConnectionToDB.class);

    private static final String DRIVER_DB = "org.postgresql.Driver";
    private static final String URL_DB = "jdbc:postgresql://localhost:5432/web-store";
    private static final String USER_DB = "postgres";
    private static final String PASSWORD_DB = "1995dxla";

    public static Connection connect(){
        Connection connection = null;
        try {
            Class.forName(DRIVER_DB);
            connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
            logger.info("Connection to DB success");
        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Connection to DB failure");
        }
        return connection;
    }
}
