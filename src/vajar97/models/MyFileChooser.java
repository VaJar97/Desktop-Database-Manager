package vajar97.models;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class MyFileChooser {

    private DatabaseQuery mQuery;

    public void chooseOpenFile(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window stage = source.getScene().getWindow();

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("  Select sql file \"*.db\"");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DB", "*.db"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            mQuery = new DatabaseQuery();
            mQuery.connection(file.getPath());
            // TODO open main stage
        }
    }

    public String chooseCreateFile(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window stage = source.getScene().getWindow();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);

        File dir = directoryChooser.showDialog(stage);
        if (dir != null) {
            return dir.getAbsolutePath();
        } else {
            return "C:/";
        }
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle(" Select directory for store database");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

}
