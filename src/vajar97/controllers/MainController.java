package vajar97.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vajar97.models.DatabaseQuery;
import vajar97.models.MyFileChooser;
import vajar97.models.Utils;

public class MainController {

    Utils utils = new Utils();
    MyFileChooser chooser = new MyFileChooser();
    DatabaseQuery mQuery;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menu_openDB;

    @FXML
    private MenuItem menu_createDB;

    @FXML
    private MenuItem menu_save;

    @FXML
    private MenuItem menu_exit;

    @FXML
    private static Menu menu_openTable;

    @FXML
    private MenuItem menu_createTable;

    @FXML
    private Label label_dbName;

    @FXML
    private Button bar_insert;

    @FXML
    private Button bar_select;

    @FXML
    private Button bar_delete;

    @FXML
    private Button bar_schema;

    @FXML
    private Button bar_query;

    @FXML
    private Button bar_update;

    @FXML
    private TableView<?> main_table;

    @FXML
    private TextArea main_queryText;

    @FXML
    private Button bar_updateTables;

    @FXML
    void initialize() {
        mQuery = new DatabaseQuery();

        bar_updateTables.setOnAction(event -> {
            updateTableView();
        });

        label_dbName.setText("You work with " + DatabaseQuery.dbName);
        menu_exit.setOnAction(this::closeApp);

        menu_createTable.setOnAction(event -> {
            utils.openScene("../views/createTable.fxml");
        });

        menu_createDB.setOnAction(event -> {
            utils.hideScene(menuBar.getScene());
            utils.openScene("../views/createDB.fxml");
        });

        menu_openDB.setOnAction(event -> {
            chooser.openFileFromMenu(menuBar);
        });


    }

    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.close();
    }

    public static void updateTableView() {
        //TODO(recognize how to update TextView)
        //Below we update menu "open tables"
        DatabaseQuery.getTableList(menu_openTable);
    }
}
