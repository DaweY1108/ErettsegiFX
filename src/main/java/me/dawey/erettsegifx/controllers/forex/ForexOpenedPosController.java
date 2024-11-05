package me.dawey.erettsegifx.controllers.forex;

import com.oanda.v20.trade.Trade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.dawey.erettsegifx.models.oanda.Oanda;

public class ForexOpenedPosController {

    @FXML
    private TableView<Trade> tableView;
    @FXML
    private TableColumn<Trade, String> idColumn;
    @FXML
    private TableColumn<Trade, String> instrumentColumn;
    @FXML
    private TableColumn<Trade, String> openTimeColumn;
    @FXML
    private TableColumn<Trade, Integer> currentUnitsColumn;
    @FXML
    private TableColumn<Trade, Double> priceColumn;
    @FXML
    private TableColumn<Trade, Double> unrealizedPLColumn;

    private ObservableList<Trade> tradeList = FXCollections.observableArrayList();

    public void initialize() {
        // Oszlopok beállítása
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        instrumentColumn.setCellValueFactory(new PropertyValueFactory<>("instrument"));
        openTimeColumn.setCellValueFactory(new PropertyValueFactory<>("openTime"));
        currentUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("currentUnits"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        unrealizedPLColumn.setCellValueFactory(new PropertyValueFactory<>("unrealizedPL"));

        // Adatok betöltése
        loadTradeData();
    }

    private void loadTradeData() {
        Oanda oanda = new Oanda();
        for (Trade trade : oanda.getTrades()) {
            tradeList.add(trade);
        }
        tableView.setItems(tradeList);
    }
}
