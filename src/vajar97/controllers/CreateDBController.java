package vajar97.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vajar97.models.DatabaseQuery;
import vajar97.models.MyFileChooser;

public class CreateDBController {

    private MyFileChooser chooser = new MyFileChooser();
    private DatabaseQuery mQuery;
    private String fullPath = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createTable_acceptBtn;

    @FXML
    private TextField createDB_dbName;

    @FXML
    private TextField createDB_dbPath;

    @FXML
    private Button createDB_directoryBtn;

    @FXML
    void initialize() {
        // get full path for new file
        createDB_directoryBtn.setOnAction(event -> {
            String path = chooser.chooseCreateFile(event) + "\\";
            fullPath = path + createDB_dbName.getText() + ".db";
            createDB_dbPath.setText(fullPath);
        });

        // check and continue
        createTable_acceptBtn.setOnAction(event -> {
            if (fullPath.equals("") || fullPath == null) {
                fullPath = System.getProperty("user.home") + createDB_dbName.getText() + ".db";
            }
            mQuery = new DatabaseQuery();
            mQuery.connection(fullPath);
            // TODO make main stage
        });
    }
}
