package vajar97.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vajar97.models.MyFileChooser;
import vajar97.models.Utils;

public class WelcomeController {

    private Utils utils = new Utils();
    private MyFileChooser chooser = new MyFileChooser();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button welcome_btn_open;

    @FXML
    private Button welcome_btn_create;

    @FXML
    void initialize() {
        welcome_btn_create.setOnAction(event -> {
            utils.switchScene(event, "../views/createDB.fxml");
        });

        welcome_btn_open.setOnAction(event -> {
            chooser.chooseOpenFile(event);
        });
    }

}

