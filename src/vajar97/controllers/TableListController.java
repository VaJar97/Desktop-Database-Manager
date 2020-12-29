package vajar97.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import vajar97.models.DatabaseQuery;
import vajar97.models.Utils;

public class TableListController {

    Utils utils = new Utils();
    private static ObservableList<String> tableList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button select_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button remove_btn;

    @FXML
    void initialize() {
        select_btn.setOnAction(event -> {
            String s = listView.getSelectionModel().getSelectedItem();
            DatabaseQuery.tableName = s;
            utils.hideScene(((Control) event.getSource()).getScene());
        });

        cancel_btn.setOnAction(event ->
            utils.hideScene(((Control) event.getSource()).getScene()));

        remove_btn.setOnAction(event -> {
            removeTable();
        });

        listView.setItems(tableList);
        listView.getSelectionModel().selectLast();
    }

    private void removeTable() {
        String s = listView.getSelectionModel().getSelectedItem();
        tableList.remove(s);
        DatabaseQuery.removeTable(s);
    }

    public void addTable(String table) {
        tableList.add(table);
    }

    public void addList(List<String> list) {
        tableList.addAll(list);
    }
}
