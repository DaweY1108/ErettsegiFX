package me.dawey.erettsegifx.controllers.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;
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
        ObservableList<String> vizsgazok = FXCollections.observableArrayList();
        for (Vizsgazo vizsgazo : vizsgazokList) {
            vizsgazok.add(Integer.toString(vizsgazo.getAzon()) + " (" + vizsgazo.getNev() + ")");
        }
        vizsgazoComboBox.setItems(vizsgazok);
    }

    @FXML
    private void handleSelectVizsgazo(ActionEvent event) {
        String selectedId = vizsgazoComboBox.getValue();
        if (selectedId == null) return;
        String[] id = selectedId.split(" ");
        selectedId = id[0];
        Vizsgazo vizsgazo = database.getVizsgazo(Integer.parseInt(selectedId));
        newNameField.setText(vizsgazo.getNev());
        newClassField.setText(vizsgazo.getOsztaly());
    }

    @FXML
    private void handleUpdateRecord(ActionEvent actionEvent) {
        String selectedId = vizsgazoComboBox.getValue();
        if (selectedId == null) return;
        String[] id = selectedId.split(" ");
        selectedId = id[0];


        Vizsgazo vizsgazo = database.getVizsgazo(Integer.parseInt(selectedId));
        if (vizsgazo != null) {
            vizsgazo.setNev(newNameField.getText());
            vizsgazo.setOsztaly(newClassField.getText());
            database.updateVizsgazo(vizsgazo);
        }
        newNameField.clear();
        newClassField.clear();
        vizsgazoComboBox.setValue(null);
        vizsgazoComboBox.setItems(FXCollections.observableArrayList());
        loadComboBoxData();
    }
}
