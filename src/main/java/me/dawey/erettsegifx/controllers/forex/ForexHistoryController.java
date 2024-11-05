package me.dawey.erettsegifx.controllers.forex;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import me.dawey.erettsegifx.models.oanda.Oanda;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.DateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ForexHistoryController {

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> currencyPairComboBox;

    @FXML
    private Button fetchDataButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<CandleData> dataTableView;

    @FXML
    private TableColumn<CandleData, String> dateColumn;

    @FXML
    private TableColumn<CandleData, Double> priceColumn;

    @FXML
    private LineChart<String, Number> priceLineChart;

    private Oanda oanda;

    public void initialize() {
        oanda = new Oanda();
        currencyPairComboBox.getItems().addAll(oanda.getInstruments());

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        fetchDataButton.setOnAction(event -> fetchAndDisplayData());

        startDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });

        endDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });
    }

    private void fetchAndDisplayData() {
        String currencyPair = currencyPairComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (currencyPair == null || startDate == null || endDate == null) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Kérjük, válasszon devizapárt és dátumokat.");
            return;
        }

        if (startDate.isAfter(endDate)) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("A kezdő dátum nem lehet későbbi, mint a befejező dátum.");
            return;
        }

        InstrumentCandlesResponse response = oanda.getHistory(currencyPair, startDate, endDate);
        if (response != null) {
            List<CandleData> candleDataList = new ArrayList<>();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(currencyPair + " árfolyam");

            for (Candlestick candle : response.getCandles()) {
                String date = candle.getTime().toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(formatter);
                double price = candle.getMid().getC().doubleValue();
                candleDataList.add(new CandleData(date, price));
                series.getData().add(new XYChart.Data<>(date, price));
            }

            dataTableView.getItems().setAll(candleDataList);
            priceLineChart.getData().clear();
            priceLineChart.getData().add(series);
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Adatok sikeresen lekérve.");
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Hiba történt az adatok lekérdezésekor.");
        }
    }

    public static class CandleData {
        private final String date;
        private final double price;

        public CandleData(String date, double price) {
            this.date = date;
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public double getPrice() {
            return price;
        }
    }
}
