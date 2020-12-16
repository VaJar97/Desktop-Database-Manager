package vajar97.models;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

public class Utils {

    public void openScene(String fileName) {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fileName));
            stage.setTitle("Desktop DB Manager");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void hideScene(Scene scene) {
        scene.getWindow()
                .hide();
    }

}
