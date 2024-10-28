package me.dawey.erettsegifx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;
import org.w3c.dom.Text;

import static me.dawey.erettsegifx.Main.database;

import java.util.List;


public class UpdateController {

    @FXML
    private ComboBox<String> vizsgazoComboBox;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField newClassField;

    @FXML
    public void initialize() {
        loadComboBoxData();
    }

    private void loadComboBoxData() {
        List<Vizsgazo> vizsgazokList = database.getAllVizsgazok();
        System.out.println(vizsgazokList.size());
        ObservableList<String> vizsgazok = FXCollections.observableArrayList();
        for (Vizsgazo vizsgazo : vizsgazokList) {
            vizsgazok.add(Integer.toString(vizsgazo.getAzon()));
        }
        vizsgazoComboBox.setItems(vizsgazok);
    }

    @FXML
    private void handleUpdateRecord(ActionEvent actionEvent) {
        String newName = newNameField.getText();
        String newClass = newClassField.getText();
        Vizsgazo vizsgazo = new Vizsgazo(newName, newClass);
        database.updateVizsgazo(vizsgazo);
        loadComboBoxData();
    }
}
