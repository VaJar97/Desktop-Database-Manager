package vajar97.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CreateTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createTable_acceptBtn;

    @FXML
    private HBox createTable_hbox;

    @FXML
    private TextField createTable_name;

    @FXML
    private ComboBox<?> createTable_type;

    @FXML
    private CheckBox createTable_ai;

    @FXML
    private CheckBox createTable_nn;

    @FXML
    private CheckBox createTable_pk;

    @FXML
    private CheckBox createTable_uq;

    @FXML
    private Button createTableBtn1;

    @FXML
    private Button createTable_cancelBtn;

    @FXML
    void initialize() {
       
    }
}
