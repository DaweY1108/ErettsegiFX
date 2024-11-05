package me.dawey.erettsegifx.controllers.forex;

import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import me.dawey.erettsegifx.models.oanda.Oanda;

import java.util.List;

public class ForexActualController {
    @FXML
    private Label price;

    @FXML
    private ComboBox<String> currency;

    @FXML
    private void initialize() {
        Oanda oanda = new Oanda();
        List<String> currencies = oanda.getInstruments();
        currency.getItems().addAll(currencies);
        price.setText("-");
    }

    public void onSelect(ActionEvent actionEvent) {
        Oanda oanda = new Oanda();
        String[] currencies = currency.getValue().split("_");
        PricingGetResponse response = oanda.getPricing(currency.getValue());
        ClientPrice prices = response.getPrices().get(0);
        String text = "1 " + currencies[0] + " ara: " + prices.getCloseoutAsk() + " " + currencies[1] ;
        price.setText(text);
    }
}
