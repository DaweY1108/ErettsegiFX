package me.dawey.erettsegifx.controllers;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;

import java.util.List;

public class ReadController {

    public TableColumn vizsgazoAzon;
    public TableColumn vizsgazoNev;
    public TableColumn vizsgazoOsztaly;

    public TableColumn vizsgatargyAzon;
    public TableColumn vizsgatargyNev;
    public TableColumn vizsgatargySzomax;
    public TableColumn vizsgatargyIrmax;

    public TableColumn vizsgaVizsgazoNev;
    public TableColumn vizsgaVizsgatargyNev;


    @FXML
    TableView tableView1; // Vizsgazo

    @FXML
    TableView tableView2; // Vizsgatargy

    @FXML
    TableView tableView3; // Vizsga

    @FXML
    void initialize() {
        refreshDatabase();
    }

    private void refreshDatabase(){
        Database database = Database.getInstance();
        try{
            List<Vizsgazo> vizsgazok = database.getAllVizsgazok();
            List<Vizsgatargy> vizsgatargyak = database.getAllVizsgatargyak();
            List<Vizsga> vizsgak = database.getAllVizsgak();

            vizsgazoAzon.setCellValueFactory(new PropertyValueFactory<Vizsgazo, Integer>("azon"));
            vizsgazoNev.setCellValueFactory(new PropertyValueFactory<Vizsgazo, String>("nev"));
            vizsgazoOsztaly.setCellValueFactory(new PropertyValueFactory<Vizsgazo, String>("osztaly"));

            vizsgatargyAzon.setCellValueFactory(new PropertyValueFactory<Vizsgatargy, Integer>("azon"));
            vizsgatargyNev.setCellValueFactory(new PropertyValueFactory<Vizsgatargy, String>("nev"));
            vizsgatargySzomax.setCellValueFactory(new PropertyValueFactory<Vizsgatargy, Integer>("szomax"));
            vizsgatargyIrmax.setCellValueFactory(new PropertyValueFactory<Vizsgatargy, Integer>("irmax"));

            vizsgaVizsgazoNev.setCellValueFactory(new PropertyValueFactory<Vizsga, String>("vizsgazoNev"));
            vizsgaVizsgatargyNev.setCellValueFactory(new PropertyValueFactory<Vizsga, String>("vizsgatargyNev"));


            tableView1.getItems().addAll(vizsgazok);
            tableView2.getItems().addAll(vizsgatargyak);
            tableView3.getItems().addAll(vizsgak);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToRead2ButtonAction() {
        Navigator.navigate(NavigationAction.READ2, Navigator.getHomeController());
    }

    public void readRefreshButtonAction(ActionEvent actionEvent) {
        tableView1.getItems().clear();
        tableView2.getItems().clear();
        tableView3.getItems().clear();
        refreshDatabase();
    }
}
