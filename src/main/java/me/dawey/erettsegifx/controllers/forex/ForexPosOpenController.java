package me.dawey.erettsegifx.controllers.forex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import me.dawey.erettsegifx.models.oanda.Oanda;

public class ForexPosOpenController {

    @FXML
    private ComboBox<String> currencyPairComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML
    private ComboBox<String> directionComboBox;

    @FXML
    private Button openPositionButton;

    @FXML
    private Label statusLabel;

    private Oanda oanda;

    public void initialize() {
        statusLabel.setText("");
        oanda = new Oanda();

        // Devizapárok betöltése
        currencyPairComboBox.getItems().addAll(oanda.getInstruments());

        // Mennyiségek betöltése (pl. 1000-es lépésekben)
        quantityComboBox.getItems().addAll(100, 500, 1000, 5000, 10000);

        // Irányok betöltése
        directionComboBox.getItems().addAll("Vétel", "Eladás");

        // Gomb eseménykezelőjének beállítása
        openPositionButton.setOnAction(event -> openPosition());
    }

    private void openPosition() {
        String instrument = currencyPairComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        String direction = directionComboBox.getValue();

        if (instrument == null || quantity == null || direction == null) {
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setText("Kérjük, válasszon minden mezőt!");
            return;
        }
        statusLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        statusLabel.setText("Kérlek várj...");

        // Mennyiség előjelének beállítása az irány alapján
        int units = direction.equals("Vétel") ? quantity : -quantity;

        // Pozíció nyitás Oanda API hívásával
        var transactionId = oanda.openTrade(instrument, units);
        if (transactionId != null) {
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            statusLabel.setText("Sikeres pozíció nyitás! Tranzakció ID: " + transactionId);
        } else {
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setText("Hiba történt a pozíció nyitásakor.");
        }
    }
}
