package vajar97.models;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import vajar97.controllers.MainController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseQuery {

    public static String dbName = "";
    public static String dbPath = "";
    public static String tableName = "";

    private final Logger LOG =  Logger.getLogger(this.getClass().getName());

    public static void getTableList(Menu menu) {
        //TODO(find error "null")
        try {
            Class.forName("org.sqlite.JDBC");
            Connection co = DriverManager.getConnection(
                    "jdbc:sqlite:" + dbPath + "");
            ResultSet rs = co.getMetaData().getTables(null, null, null, null);
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(rs.getString("TABLE_NAME"));
                menu.getItems().add(menuItem);
                menuItem.setOnAction(event -> {
                    tableName = ((MenuItem) event.getSource()).getText();
                    System.out.println("open new table : " + ((MenuItem) event.getSource()).getText());
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void connection(String path) {
        try {
            Class.forName("org.sqlite.JDBC");    // get instance of JDBC-class
            Connection co = DriverManager.getConnection(
                    "jdbc:sqlite:" + path + "");    //  connecting new db with driver

            if (co != null & !path.equals("")) {
                Path p = Paths.get(path);
                dbName = p.getFileName().toString();
                dbPath = path;
            }

            LOG.info("Database " + dbName + " connected");

        } catch (Exception ex) {
            System.out.print(ex.getMessage() + "\nError: Database or driver not connected");
        }
    }

    public void createTable(
            String name,
            boolean notExist,
            List<String> columnNamesList,
            List<String> typesList,
            List<Boolean> aiValueList,
            List<Boolean> nnValueList,
            List<Boolean> pkValueList,
            List<Boolean> uqValueList,
            List<String> defaultValueList
    ) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection co = DriverManager.getConnection(
                    "jdbc:sqlite:" + dbPath + "");

            String query = "CREATE TABLE " + (notExist ? "IF NOT EXISTS " : " ")
                    + (name.equals("") ? "newTable" : name) + " (";

            for (int i = 0; i < columnNamesList.size(); i++) {
                if (i == columnNamesList.size() - 1) {
                    query += "\n    " + columnNamesList.get(i) + " " + typesList.get(i) + " "
                            + (pkValueList.get(i) ? "PRIMARY KEY " : " ")
                            + (aiValueList.get(i) ? "AUTOINCREMENT " : " ")
                            + (nnValueList.get(i) ? "NOT NULL " : " ")
                            + (uqValueList.get(i) ? "UNIQUE " : " ")
                            + (defaultValueList.get(i).equals("") ? "" : "DEFAULT " + defaultValueList.get(i));
                } else {
                    query += "\n    " + columnNamesList.get(i) + " " + typesList.get(i) + " "
                            + (pkValueList.get(i) ? "PRIMARY KEY " : " ")
                            + (aiValueList.get(i) ? "AUTOINCREMENT " : " ")
                            + (nnValueList.get(i) ? "NOT NULL " : " ")
                            + (uqValueList.get(i) ? "UNIQUE " : " ")
                            + (defaultValueList.get(i).equals("") ? "" : "DEFAULT " + defaultValueList.get(i)) + ",";
                }
            }
            query += "\n);";
            System.out.println(query);
            Statement stmt = co.createStatement();
                // create a new table
                stmt.execute(query);
                LOG.info("Tables " + name + " created");
                MainController.updateTableView();
                tableName = name;
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void getData() {
        //TODO(get column name and type)
        try {
            Class.forName("org.sqlite.JDBC");
            Connection co = DriverManager.getConnection(
                    "jdbc:sqlite:" + dbPath + "");

            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from " + tableName + "");
            ResultSetMetaData rsmd = rs.getMetaData();
            int colType = rsmd.getColumnType(1);
            System.out.println("Column 1 is type " + colType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
