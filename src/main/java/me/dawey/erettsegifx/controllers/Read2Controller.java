package me.dawey.erettsegifx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import javafx.scene.control.TableView;
import java.util.List;

public class Read2Controller {
    @FXML
    public Button backButton;
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
        vizsgaList = FXCollections.observableArrayList(vizsgaListFromDb);


        vizsgaTable.setItems(vizsgaList);

    }
}
