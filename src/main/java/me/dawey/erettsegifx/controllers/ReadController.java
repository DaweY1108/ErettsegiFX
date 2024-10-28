package me.dawey.erettsegifx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.dawey.erettsegifx.Main;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;

import java.util.List;

import static me.dawey.erettsegifx.Main.database;

public class ReadController {

    @FXML
    public Button btGoToReadTwo;
    @FXML
    private TableView<Vizsga> vizsgaTable;

    @FXML
    private TableColumn<Vizsga, Vizsgazo> vizsgazoColumn;

    @FXML
    private TableColumn<Vizsga, Vizsgatargy> vizsgatargyColumn;

    @FXML
    private TableColumn<Vizsga, Integer> szobeliColumn;

    @FXML
    private TableColumn<Vizsga, Integer> irasbeliColumn;

    private ObservableList<Vizsga> vizsgaList;

    public void initialize() {
        vizsgazoColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgazo"));
        vizsgatargyColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgatargy"));
        szobeliColumn.setCellValueFactory(new PropertyValueFactory<>("szobeli"));
        irasbeliColumn.setCellValueFactory(new PropertyValueFactory<>("irasbeli"));

        List<Vizsga> vizsgaListFromDb = database.getAllVizsgak();
        //List<Vizsgazo> vizsgazoListFromDb = database.getAllVizsgazok();

        vizsgaList = FXCollections.observableArrayList(vizsgaListFromDb);
        vizsgaTable.setItems(vizsgaList);

    }

    public void goToReadTwo() {
        Navigator.navigate(NavigationAction.READ2, Navigator.getHomeController());
    }
}
