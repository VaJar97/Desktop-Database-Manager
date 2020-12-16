package vajar97.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseQuery {

    private Connection co;
    private Statement statement;

    public static String dbName = "";
    public static String dbPath = "";

    private final Logger LOG =  Logger.getLogger(this.getClass().getName());

    public void connection(String path) {
        try {
            Class.forName("org.sqlite.JDBC");    // get instance of JDBC-class
            co = DriverManager.getConnection(
                    "jdbc:sqlite:" + path + "");    //  connecting new db with driver

            if (co != null & !path.equals("")) {
                Path p = Paths.get(path);
                dbName = p.getFileName().toString();
                dbPath = path;
            }

            LOG.info("Database " + dbName + " connected");

        } catch (Exception ex) {
            System.out.print(ex.getMessage() + "\nError: Database not connected");
        }
    }
}
