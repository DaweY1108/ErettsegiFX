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
    private TableColumn vizsgaAzonColumn;
    @FXML
    private TableView vizsgazoTable;
    @FXML
    private TableColumn vizsgazoAzonColumn;
    @FXML
    private TableColumn vizsgazoNameColumn;
    @FXML
    private TableColumn vizsgazoClassNameColumn;
    @FXML
    private TableColumn vizsgatargyAzonColumn;
    @FXML
    private TableView vizsgatargyTable;
    @FXML
    private TableColumn vizsgatargyNameColumn;
    @FXML
    private TableColumn irmaxColumn;
    @FXML
    private TableColumn szomaxColumn;
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
        // Vizsgak
        vizsgaAzonColumn.setCellValueFactory(new PropertyValueFactory<>("azon"));
        vizsgazoColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgazo"));
        vizsgatargyColumn.setCellValueFactory(new PropertyValueFactory<>("vizsgatargy"));
        szobeliColumn.setCellValueFactory(new PropertyValueFactory<>("szobeli"));
        irasbeliColumn.setCellValueFactory(new PropertyValueFactory<>("irasbeli"));

        List<Vizsga> vizsgaListFromDb = database.getAllVizsgak();
        ObservableList<Vizsga> vizsgaList = FXCollections.observableArrayList(vizsgaListFromDb);
        vizsgaTable.setItems(vizsgaList);

        // Vizsgazok

        vizsgazoAzonColumn.setCellValueFactory(new PropertyValueFactory<>("azon"));
        vizsgazoNameColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        vizsgazoClassNameColumn.setCellValueFactory(new PropertyValueFactory<>("osztaly"));

        List<Vizsgazo> vizsgazoListFromDb = database.getAllVizsgazok();
        ObservableList<Vizsgazo> vizsgazoList = FXCollections.observableArrayList(vizsgazoListFromDb);
        vizsgazoTable.setItems(vizsgazoList);


        // Vizsgatargyak

        vizsgatargyAzonColumn.setCellValueFactory(new PropertyValueFactory<>("azon"));
        vizsgatargyNameColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        irmaxColumn.setCellValueFactory(new PropertyValueFactory<>("irmax"));
        szomaxColumn.setCellValueFactory(new PropertyValueFactory<>("szomax"));

        List<Vizsgatargy> vizsgatargyListFromDb = database.getAllVizsgatargyak();
        ObservableList<Vizsgatargy> vizsgatargyList = FXCollections.observableArrayList(vizsgatargyListFromDb);
        vizsgatargyTable.setItems(vizsgatargyList);


    }

    public void goToReadTwo() {
        Navigator.navigate(NavigationAction.READ2, Navigator.getHomeController());
    }
}
