package me.dawey.erettsegifx.controllers.soap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import me.dawey.erettsegifx.models.mnbank.BankManager;
import me.dawey.erettsegifx.models.mnbank.data.MNBCurrentExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRatesQueryValues;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SoapDownloadController {

    @FXML
    private Label soapDownloadLabel;

    @FXML
    private void initialize() {
        soapDownloadLabel.setText("");
        soapDownloadLabel.setTextFill(Color.GREEN);
    }

    public void soapDownloadButtonAction() {
        soapDownloadLabel.setTextFill(Color.GREEN);
        soapDownloadLabel.setText("Adatok letöltése...");
        // Könyvtár választása
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Kérlek, válassz egy könyvtárat, ahova mentsük az MNB adatokat!");
        File selectedDirectory = dc.showDialog(null);

        if (selectedDirectory != null) {

            // Fájl létrehozása a kiválasztott könyvtárban
            File outputFile = new File(selectedDirectory, "MNB.txt");

            try (FileWriter writer = new FileWriter(outputFile)) {
                BankManager bankManager = new BankManager();
                List<MNBExchangeRates.Day> days = bankManager.getExchangeRatesList("2021-01-01", "2022-01-01", "EUR");
                List<MNBCurrentExchangeRates.Rate> currentRates = bankManager.getCurrentExchangeRatesList();
                List<String> currencies = bankManager.getCurrencies();
                writer.write("Pénznemek: " + currencies + "\n\n");

                // Aktuális árfolyamok kiírása
                writer.write("Aktuális árfolyamok:\n");
                for (MNBCurrentExchangeRates.Rate rate : currentRates) {
                    writer.write(rate.getCurrency() + " " + rate.getValue() + "\n");
                }

                // Árfolyamok kiírása
                writer.write("\nEuró Árfolyamváltozások (2021. 01. 01. - 2022. 01. 01.):\n");
                for (MNBExchangeRates.Day day : days) {
                    writer.write(day.getDate() + " -> ");
                    for (MNBExchangeRates.Rate rate : day.getRates()) {
                        writer.write(rate.getValue() + "\n");
                    }
                    writer.write("\n");
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
