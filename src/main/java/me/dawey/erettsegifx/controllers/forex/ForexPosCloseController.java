package me.dawey.erettsegifx.controllers.forex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import me.dawey.erettsegifx.models.oanda.Oanda;
import com.oanda.v20.trade.Trade;

public class ForexPosCloseController {

    @FXML
    private ComboBox<String> positionIdComboBox;

    @FXML
    private Button closePositionButton;

    @FXML
    private Label statusLabel;

    private Oanda oanda;

    public void initialize() {
        oanda = new Oanda();

        // Nyitott pozíciók betöltése a ComboBox-ba
        loadOpenPositions();

        // Gomb eseménykezelő beállítása
        closePositionButton.setOnAction(event -> closePosition());
    }

    private void loadOpenPositions() {
        positionIdComboBox.getItems().clear();
        var trades = oanda.getTrades();
        if (trades != null) {
            for (Trade trade : trades) {
                positionIdComboBox.getItems().add(trade.getId().toString());
            }
        } else {
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setText("Hiba történt a pozíciók lekérdezésekor.");
        }
    }

    private void closePosition() {
        String positionId = positionIdComboBox.getValue();
        if (positionId == null || positionId.isEmpty()) {
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setText("Kérjük, válasszon egy pozíció ID-t!");
            return;
        }

        // Pozíció zárása az Oanda API hívásával
        boolean success = oanda.closeTrade(positionId);
        if (success) {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("A pozíció sikeresen lezárva.");
            loadOpenPositions(); // Frissíti a ComboBox-ot a nyitott pozíciók új listájával
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Hiba történt a pozíció zárásakor.");
        }
    }
}