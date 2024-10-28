package me.dawey.erettsegifx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;

import java.util.List;

import static me.dawey.erettsegifx.Main.database;

public class DeleteController {

    @FXML
    private TableView vizsgaTable;
    @FXML
    private TableColumn vizsgaAzonColumn;
    @FXML
    private TableColumn vizsgazoColumn;
    @FXML
    private TableColumn vizsgatargyColumn;
    @FXML
    private TableColumn szobeliColumn;
    @FXML
    private TableColumn irasbeliColumn;

    @FXML
    private TableView vizsgazoTable;
    @FXML
    private TableColumn vizsgazoAzonColumn;
    @FXML
    private TableColumn vizsgazoNameColumn;
    @FXML
    private TableColumn vizsgazoClassNameColumn;

    @FXML
    private TableView vizsgatargyTable;
    @FXML
    private TableColumn vizsgatargyNameColumn;
    @FXML
    private TableColumn irmaxColumn;
    @FXML
    private TableColumn szomaxColumn;

    @FXML
    private void initialize(){
        vizsgaTable.setOnMouseClicked(this::handleDeleteVizsga);
        vizsgazoTable.setOnMouseClicked(this::handleDeleteVizsgazo);
        vizsgatargyTable.setOnMouseClicked(this::handleDeleteVizsgatargy);

        // Vizsgak

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

        vizsgatargyNameColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        irmaxColumn.setCellValueFactory(new PropertyValueFactory<>("irmax"));
        szomaxColumn.setCellValueFactory(new PropertyValueFactory<>("szomax"));

        List<Vizsgatargy> vizsgatargyListFromDb = database.getAllVizsgatargyak();
        ObservableList<Vizsgatargy> vizsgatargyList = FXCollections.observableArrayList(vizsgatargyListFromDb);
        vizsgatargyTable.setItems(vizsgatargyList);

    }

    private void handleDeleteVizsga(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click to delete
            Vizsga selectedVizsga = (Vizsga) vizsgaTable.getSelectionModel().getSelectedItem();
            if (selectedVizsga != null) {
                System.out.println("Deleting vizsga: " + selectedVizsga.getVizsgazo().getNev() + " " + selectedVizsga.getVizsgatargy().getNev());
                deleteVizsga(selectedVizsga);
                vizsgaTable.getItems().remove(selectedVizsga);
            }
        }
    }

    private void handleDeleteVizsgazo(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click to delete
            Vizsgazo selectedVizsgazo = (Vizsgazo) vizsgazoTable.getSelectionModel().getSelectedItem();
            if (selectedVizsgazo != null) {
                System.out.println("Deleting vizsgazo: " + selectedVizsgazo.getNev());
                deleteVizsgazo(selectedVizsgazo);
                vizsgazoTable.getItems().remove(selectedVizsgazo);
            }
        }
    }

    private void handleDeleteVizsgatargy(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click to delete
            Vizsgatargy selectedVizsgatargy = (Vizsgatargy) vizsgatargyTable.getSelectionModel().getSelectedItem();
            if (selectedVizsgatargy != null) {
                System.out.println("Deleting vizsgatargy: " + selectedVizsgatargy.getNev());
                deleteVizsgatargy(selectedVizsgatargy);
                vizsgatargyTable.getItems().remove(selectedVizsgatargy);
            }
        }
    }

    private void deleteVizsgazo(Vizsgazo vizsgazo) {
        database.deleteVizsgazo(vizsgazo);
    }

    private void deleteVizsgatargy(Vizsgatargy vizsgatargy) {
        database.deleteVizsgatargy(vizsgatargy);
    }

    private void deleteVizsga(Vizsga vizsga) {
        database.deleteVizsga(vizsga);
    }
}
