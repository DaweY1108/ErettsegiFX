package me.dawey.erettsegifx.controllers.soap;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import me.dawey.erettsegifx.models.mnbank.BankManager;
import me.dawey.erettsegifx.models.mnbank.data.MNBCurrentExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SoapDownload2Controller {


    @FXML
    private CheckBox currencies;

    @FXML
    private CheckBox actualRates;

    @FXML
    private CheckBox currencyRates;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private ComboBox<String> currencyChoiceBox;

    @FXML
    private Label soapDownloadLabel;

    @FXML
    private void initialize() {
        soapDownloadLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        soapDownloadLabel.setText("");
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
        System.out.println("SoapDownload2Controller initialized");
    }

    public void soapDownloadButtonAction() {
        soapDownloadLabel.setTextFill(Color.GREEN);
        soapDownloadLabel.setText("Adatok letöltése...");
        String fromDateString = "";
        String toDateString = "";

        if (currencyRates.isSelected()) {
            if (dateFrom.getValue() == null || dateTo.getValue() == null || currencyChoiceBox.getValue() == null) {
                soapDownloadLabel.setTextFill(Color.RED);
                soapDownloadLabel.setText("Kérlek válaszd ki a dátumot és a pénznemet!");
                return;
            }

            if (dateFrom.getValue().isAfter(dateTo.getValue())) {
                soapDownloadLabel.setTextFill(Color.RED);
                soapDownloadLabel.setText("A kezdő dátum nem lehet későbbi, mint a befejező dátum!");
                return;
            }
            fromDateString = dateFrom.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            toDateString = dateTo.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        if (!currencies.isSelected() && !actualRates.isSelected() && !currencyRates.isSelected()) {
            soapDownloadLabel.setTextFill(Color.RED);
            soapDownloadLabel.setText("Kérlek válassz legalább egy opciót!");
            return;
        }

        // Könyvtár választása
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Kérlek, válassz egy könyvtárat, ahova mentsük az MNB adatokat!");
        File selectedDirectory = dc.showDialog(null);

        if (selectedDirectory != null) {

            // Fájl létrehozása a kiválasztott könyvtárban
            File outputFile = new File(selectedDirectory, "MNB.txt");

            try (FileWriter writer = new FileWriter(outputFile)) {
                BankManager bankManager = new BankManager();

                if (currencies.isSelected()) {
                    List<String> currencies = bankManager.getCurrencies();
                    writer.write("Pénznemek: " + currencies + "\n\n");
                }

                if (actualRates.isSelected()) {
                    List<MNBCurrentExchangeRates.Rate> currentRates = bankManager.getCurrentExchangeRatesList();
                    // Aktuális árfolyamok kiírása
                    writer.write("Aktuális árfolyamok:\n");
                    for (MNBCurrentExchangeRates.Rate rate : currentRates) {
                        writer.write(rate.getCurrency() + " " + rate.getValue() + "\n");
                    }
                }

                if (currencyRates.isSelected()) {

                    List<MNBExchangeRates.Day> days = bankManager.getExchangeRatesList(fromDateString, toDateString, currencyChoiceBox.getValue());

                    // Árfolyamok kiírása
                    writer.write("\n" + currencyChoiceBox.getValue() +" Árfolyamváltozások (" + fromDateString + " - " + toDateString + "):\n");
                    for (MNBExchangeRates.Day day : days) {
                        writer.write(day.getDate() + " -> ");
                        if (day.getRates() == null) {
                            soapDownloadLabel.setTextFill(Color.RED);
                            soapDownloadLabel.setText("Nincs adat a megadott időszakra, vagy a megadott pénznemre");
                            return;
                        }
                        for (MNBExchangeRates.Rate rate : day.getRates()) {
                            writer.write(rate.getValue() + "\n");
                        }
                    }
                }

                System.out.println("Data saved to file: " + outputFile.getAbsolutePath());
                soapDownloadLabel.setText("Adatok letöltve: " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                soapDownloadLabel.setTextFill(Color.RED);
                soapDownloadLabel.setText("Hiba a fájl írása közben.");
            }
        } else {
            System.out.println("No directory selected.");
            soapDownloadLabel.setTextFill(Color.RED);
            soapDownloadLabel.setText("Nincs könyvtár kiválasztva.");
        }
    }
}

