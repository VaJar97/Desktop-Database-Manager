package vajar97.models;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import vajar97.controllers.MainController;
import vajar97.controllers.TableListController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseQuery {

    public static String dbName = "";
    public static String dbPath = "";
    public static String tableName = "";
    private static List<String> tableList;

    private static Connection conn;

    private final Logger LOG =  Logger.getLogger(this.getClass().getName());

    public void connection(String path) {
        try {
            Class.forName("org.sqlite.JDBC");    // get instance of JDBC-class
            conn = DriverManager.getConnection(
                    "jdbc:sqlite:" + path + "");    //  connecting new db with driver

            if (conn != null & !path.equals("")) {
                Path p = Paths.get(path);
                dbName = p.getFileName().toString();
                dbPath = path;
            }

            LOG.info("Database " + dbName + " connected");
            getTableList();
            TableListController tlc = new TableListController();
            tlc.addList(tableList);

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
            Statement stmt = conn.createStatement();
                // create a new table
                stmt.execute(query);
                LOG.info("Tables " + name + " created");
                tableName = name;
                addTable(tableName);
                tableList.add(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getData() {
        //TODO(get column name and type)
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from " + tableName + "");
            int index = 1;
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int colType = rsmd.getColumnType(index);
                String colName = rsmd.getColumnName(index);
                System.out.println(
                        index + ". Table: " + rsmd.getTableName(index) + " - Column " + colName +  " is type " + colType);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTableList() {
        try {
            ResultSet rs = conn.getMetaData().getTables("", "", "", null);
            tableList = new ArrayList<>();
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
                tableList.add(table);
                tableName = table;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTable(String tableName) {
        tableList.add(tableName);
        TableListController tlc = new TableListController();
        tlc.addTable(tableName);
    }

    public static void removeTable(String name) {
        try {
            Statement stm = conn.createStatement();
            stm.execute("DROP TABLE " + name + ";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
