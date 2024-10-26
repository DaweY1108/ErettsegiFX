package me.dawey.erettsegifx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsga;

import java.util.List;

public class Read2Controller {
    @FXML
    public Button backButton;
    public CheckBox vizsgazoCheckBox;
    public CheckBox vizsgatargyCheckBox;
    public CheckBox classCheckBox;
    public ToggleGroup caseSensitivityGroup;
    public RadioButton caseSensitiveRadioButton;
    public RadioButton notCaseSensitiveRadioButton;
    @FXML
    private TextField vizsgazoNameField;
    @FXML
    private TextField vizsgatargyNameField;
    @FXML
    private TableColumn vizsgazoColumn;
    @FXML
    private TableColumn vizsgatargyColumn;
    @FXML
    private TableColumn szobeliColumn;
    @FXML
    private TableColumn irasbeliColumn;
    @FXML
    private ObservableList<Vizsga> vizsgaList;
    @FXML
    private TableView<Vizsga> vizsgaTable;
    @FXML
    private ComboBox<String> classComboBox;
    @FXML
    public void initialize() {
        System.out.println("Read2Controller initialized");
    }

    @FXML
    public void goToRead() {
        Navigator.navigate(NavigationAction.READ, Navigator.getHomeController());
    }


    public void onFileredQueryRequest(ActionEvent actionEvent) {
        vizsgazoColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgazo"));
        vizsgatargyColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgatargy"));
        szobeliColumn.setCellValueFactory(new PropertyValueFactory<>("szobeli"));
        irasbeliColumn.setCellValueFactory(new PropertyValueFactory<>("irasbeli"));

        Database database = new Database();
        List<Vizsga> vizsgaListFromDb = database.getAllVizsgak();

        String vizsgazoName = vizsgazoNameField.getText();
        String vizsgatargyName = vizsgatargyNameField.getText();
        boolean caseSensitive = caseSensitiveRadioButton.isSelected();

        if (vizsgazoName != null && !vizsgazoName.isEmpty() && vizsgazoCheckBox.isSelected()) {
            if (caseSensitive) {
                vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgazo().getNev().contains(vizsgazoName));
            } else {
                vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgazo().getNev().toLowerCase().contains(vizsgazoName.toLowerCase()));
            }
        }

        if (vizsgatargyName != null && !vizsgatargyName.isEmpty() && vizsgatargyCheckBox.isSelected()) {
            if (caseSensitive) {
                vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgatargy().getNev().contains(vizsgatargyName));
            } else {
                vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgatargy().getNev().toLowerCase().contains(vizsgatargyName.toLowerCase()));
            }
        }

        if (classComboBox.getValue() != null && !classComboBox.getValue().isEmpty() && classCheckBox.isSelected()) {
            vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgazo().getOsztaly().contains(classComboBox.getValue()));
        }

        vizsgaList = FXCollections.observableArrayList(vizsgaListFromDb);
        vizsgaTable.setItems(vizsgaList);
    }
}
