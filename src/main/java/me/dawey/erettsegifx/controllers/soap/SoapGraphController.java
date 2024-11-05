package me.dawey.erettsegifx.controllers.soap;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import me.dawey.erettsegifx.models.mnbank.BankManager;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SoapGraphController {
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private ComboBox<String> currencyChoiceBox;

    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        errorLabel.setTextFill(Color.RED);
        errorLabel.setText("");
        lineChart.setTitle("Árfolyam változás");
        xAxis.setLabel("Dátum");
        yAxis.setLabel("Árfolyam");

        dateFrom.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });

        dateTo.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });
        BankManager bankManager = new BankManager();
        List<String> currencies = bankManager.getCurrencies();
        currencies.removeAll(List.of("HUF"));
        currencyChoiceBox.getItems().addAll(currencies);
        currencyChoiceBox.getSelectionModel().selectFirst();
        System.out.println("SoapGraphController initialized");
    }

    public void graphButtonAction() {
        if (dateFrom.getValue() == null || dateTo.getValue() == null || currencyChoiceBox.getValue() == null) {
            errorLabel.setText("Kérlek válaszd ki a dátumot és a pénznemet!");
            return;
        }

        if (dateFrom.getValue().isAfter(dateTo.getValue())) {
            errorLabel.setText("A kezdő dátum nem lehet későbbi, mint a befejező dátum!");
            return;
        }

        errorLabel.setText("");

        String fromDateString = dateFrom.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String toDateString = dateTo.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        BankManager bankManager = new BankManager();
        List<MNBExchangeRates.Day> days = bankManager.getExchangeRatesList(fromDateString, toDateString, currencyChoiceBox.getValue());
        Collections.reverse(days);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(currencyChoiceBox.getValue());

        for (MNBExchangeRates.Day day : days) {
            if (day.getRates() == null) {
                errorLabel.setText("Nincs adat a megadott időszakra, vagy a megadott pénznemre");
                return;
            }
            String date = day.getDate(); // Dátum Stringként

            for (MNBExchangeRates.Rate rate : day.getRates()) {
                String rateValue = rate.getValue();
                rateValue = rateValue.replace(",", "."); // Tizedesvessző -> tizedespont
                series.getData().add(new XYChart.Data<>(date, Double.parseDouble(rateValue)));
            }
        }

        lineChart.getData().clear(); // Clear previous data
        lineChart.getData().add(series);
    }
}
