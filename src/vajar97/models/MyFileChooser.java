package vajar97.models;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vajar97.controllers.MainController;

import java.io.File;

public class MyFileChooser {

    private DatabaseQuery mQuery;
    private Utils utils = new Utils();

    public void chooseOpenFile(ActionEvent event) {

        Stage stage = eventToStage(event);

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(" Select file \"*.db\" to open");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.setTitle("  Select sql file \"*.db\"");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DB", "*.db"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            mQuery = new DatabaseQuery();
            mQuery.connection(file.getPath());

            utils.hideScene(
                    ((Control) event.getSource()).getScene()
            );
            utils.openScene("../views/mainStage.fxml");
        }
    }

    public String chooseCreateFile(ActionEvent event) {

        Stage stage = eventToStage(event);

        final DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle(" Select directory for store database");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File dir = directoryChooser.showDialog(stage);
        if (dir != null) {
            return dir.getAbsolutePath();
        } else {
            return "C:/";
        }
    }

    private static Stage eventToStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }

    public void openFileFromMenu(MenuBar menuBar) {
        Stage stage = (Stage) menuBar.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(" Select file \"*.db\" to open");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.setTitle("  Select sql file \"*.db\"");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DB", "*.db"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            mQuery = new DatabaseQuery();
            mQuery.connection(file.getPath());
            utils.hideScene(menuBar.getScene());
            utils.openScene("../views/mainStage.fxml");
        }
    }
}
