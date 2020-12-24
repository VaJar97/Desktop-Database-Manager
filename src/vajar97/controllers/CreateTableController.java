package vajar97.controllers;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import vajar97.models.DatabaseQuery;
import vajar97.models.Utils;

public class CreateTableController {

    private Utils utils = new Utils();
    private DatabaseQuery mQuery = new DatabaseQuery();

    private final ObservableList<String> TYPE_ITEM_LIST = FXCollections.observableArrayList("INTEGER", "TEXT", "REAL", "TIMESTAMP");
    private final Logger LOG =  Logger.getLogger(this.getClass().getName());

    private ObservableList<HBox> hboxList = FXCollections.observableArrayList();
    private List<TextField> textViewList;
    private List<ComboBox<String>> comboBoxList;
    private List<CheckBox> aiList;
    private List<CheckBox> nnList;
    private List<CheckBox> pkList;
    private List<CheckBox> uqList;
    private List<TextField> defaultViewList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createTable_acceptBtn;

    @FXML
    private Menu menuFile;

    @FXML
    private Menu menuTables;

    @FXML
    private Button createTable_addBtn;

    @FXML
    private Button createTable_cancelBtn;

    @FXML
    private ListView<HBox> createTable_vbox;

    @FXML
    private TextField createTable_tableName;

    @FXML
    private CheckBox createTable_ifNotExists;

    @FXML
    private Button createTable_removeBtn;

    @FXML
    void initialize() {
        createTable_addBtn.setOnAction(event -> addNewHBox());
        createTable_cancelBtn.setOnAction(event -> utils.hideScene(((Control) event.getSource()).getScene()));
        createTable_acceptBtn.setOnAction(event -> prepareData());
        createTable_removeBtn.setOnAction(this::removeRow);
        createTable_vbox.setItems(hboxList);

        textViewList = new ArrayList<>();
        comboBoxList = new ArrayList<>();
        aiList = new ArrayList<>();
        nnList = new ArrayList<>();
        pkList = new ArrayList<>();
        uqList = new ArrayList<>();
        defaultViewList = new ArrayList<>();
    }

    private void prepareData() {
        List<String> columnNamesList = new ArrayList<>();
        fillLists(textViewList, columnNamesList);
        List<String> typesList = new ArrayList<>();
        fillLists(comboBoxList, typesList);
        List<Boolean> aiValueList = new ArrayList<>();
        fillLists(aiList, aiValueList);
        List<Boolean> nnValueList = new ArrayList<>();
        fillLists(nnList, nnValueList);
        List<Boolean> pkValueList = new ArrayList<>();
        fillLists(pkList, pkValueList);
        List<Boolean> uqValueList = new ArrayList<>();
        fillLists(uqList, uqValueList);
        List<String> defaultValueList = new ArrayList<>();
        fillLists(defaultViewList, defaultValueList);

        mQuery.createTable(
                createTable_tableName.getText(), createTable_ifNotExists.isSelected(), columnNamesList, typesList, aiValueList, nnValueList, pkValueList, uqValueList, defaultValueList);
    }

    private <T extends Control, P extends Object> void fillLists(List<T> viewList, List<P> valueList) {
        if (!(viewList.isEmpty() && valueList.isEmpty())) {
            for (T view : viewList) {
                switch (view.getClass().getSimpleName()) {
                    case ("TextField"):
                        TextField t = (TextField) view;
                        valueList.add((P) t.getText());
                        break;
                    case ("ComboBox"):
                        ComboBox<String> combo = (ComboBox<String>) view;
                        valueList.add((P) combo.getSelectionModel().getSelectedItem());
                        break;
                    case ("CheckBox"):
                        CheckBox check = (CheckBox) view;
                        Boolean b = check.isSelected();
                        valueList.add((P) b);
                        break;
                    default: //TODO(make default);
                }
            }
        } else {
            LOG.info("Error in method fillLists, lists are empty");
        }
    }

    private void removeRow(ActionEvent event) {
        HBox selectedItem = createTable_vbox.getSelectionModel().getSelectedItem();

        createTable_vbox.getItems().remove(selectedItem);
            hboxList.remove(selectedItem);
    }

    private void checkPrimaryKey(ActionEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        for (CheckBox item: pkList) {
            if (item.isSelected() && !(item.equals(checkBox))) {
                item.setSelected(false);
                checkBox.setSelected(true);
                break;
            }
        }
    }

    private void checkColumnType(CheckBox checkBox) {
        HBox Hbox = (HBox) checkBox.getParent();
        int index = createTable_vbox.getItems().indexOf(Hbox);
        String result = comboBoxList.get(index).getValue();
        if (result.equals("INTEGER") && !(checkBox.isSelected())) {
            checkBox.setSelected(false);
        } else {
            if (!(result.equals("INTEGER")) && !(checkBox.isSelected())) {
                checkBox.setSelected(false);
            } else {
                if (!(result.equals("INTEGER")) && checkBox.isSelected()) {
                    checkBox.setSelected(false);
                } else {
                    if (result.equals("INTEGER")) {
                        checkBox.setSelected(true);
                    }
                }
            }
        }
    }

    private void addNewHBox() {

        TextField textField = new TextField();
        textField.setPrefWidth(135);
        textViewList.add(textField);

        CheckBox ai = new CheckBox();
        aiList.add(ai);
        ai.setOnAction(event -> {
            checkColumnType(ai);
        });
        CheckBox nn = new CheckBox();
        nnList.add(nn);
        CheckBox pk = new CheckBox();
        pkList.add(pk);
        pk.setOnAction(this::checkPrimaryKey);
        CheckBox uq = new CheckBox();
        uqList.add(uq);

        ComboBox<String> comboBox = new ComboBox<>(TYPE_ITEM_LIST);
        comboBox.setPrefWidth(100);
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(event -> {
            checkColumnType(ai);
            if (((ComboBox<String>) event.getSource()).getValue().equals("TIMESTAMP")) {
                //TODO(make default value to timestamp)
            }
        });
        comboBoxList.add(comboBox);

        TextField defaultField = new TextField();
        defaultField.setPrefWidth(100);
        defaultViewList.add(defaultField);

        hboxList.add(new HBox(
                20,
                textField,
                comboBox,
                ai,
                nn,
                pk,
                uq,
                defaultField
        ));
        createTable_vbox.getSelectionModel().selectLast();
    }

}
