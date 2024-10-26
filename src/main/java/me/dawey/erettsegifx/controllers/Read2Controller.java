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

        if (vizsgazoName != null && !vizsgazoName.isEmpty() && vizsgazoCheckBox.isSelected()) {
            vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgazo().getNev().contains(vizsgazoName));
        }

        if (vizsgatargyName != null && !vizsgatargyName.isEmpty() && vizsgatargyCheckBox.isSelected()) {
            vizsgaListFromDb.removeIf(vizsga -> !vizsga.getVizsgatargy().getNev().contains(vizsgatargyName));
        }

        vizsgaList = FXCollections.observableArrayList(vizsgaListFromDb);
        vizsgaTable.setItems(vizsgaList);
    }
}
