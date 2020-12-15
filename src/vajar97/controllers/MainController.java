package vajar97.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem menu_openDB;

    @FXML
    private MenuItem menu_createDB;

    @FXML
    private MenuItem menu_exit;

    @FXML
    private MenuItem menu_openTable;

    @FXML
    private MenuItem menu_createTable;

    @FXML
    private Button bar_insert;

    @FXML
    private Button bar_select;

    @FXML
    private Button bar_delete;

    @FXML
    private Button bar_schema;

    @FXML
    private TextArea mainText;

    @FXML
    void initialize() {

    }
}
