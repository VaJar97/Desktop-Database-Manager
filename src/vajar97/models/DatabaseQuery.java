package vajar97.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseQuery {

    public Connection co;
    public Statement statement;
    Logger logger =  Logger.getLogger(this.getClass().getName());

    public void connection(String path) {
        try {
            Class.forName("org.sqlite.JDBC");    // get instance of JDBC-class
            co = DriverManager.getConnection(
                    "jdbc:sqlite:" + path + "");    //  connecting new db with driver

            logger.info("Database connected");

        } catch (Exception ex) {
            System.out.print(ex.getMessage() + "\nError: Database not connected");
        }
    }
}
