package me.dawey.erettsegifx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;
import me.dawey.erettsegifx.models.mnbank.BankManager;
import me.dawey.erettsegifx.models.mnbank.data.Day;
import me.dawey.erettsegifx.models.mnbank.data.ExchangeData;
import me.dawey.erettsegifx.models.mnbank.data.MNBExchangeRates;
import me.dawey.erettsegifx.models.mnbank.data.Rate;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    //Start the application
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ErettsegiFX");
        stage.setScene(scene);
        stage.show();
        Database database = new Database();

        List<Vizsgazo> vizsgazok = database.getAllVizsgazok();
        for (Vizsgazo vizsgazo : vizsgazok) {
            System.out.println(vizsgazo.getNev());
        }

        BankManager bankManager = new BankManager();
        List<ExchangeData> exchangeDataList = bankManager.getExchangeDatas("2022-08-14", "2022-09-14", "EUR");
        for (ExchangeData exchangeData : exchangeDataList) {
            System.out.println("Date: " + exchangeData.getDate());
            System.out.println("Currency: " + exchangeData.getCurrency());
            System.out.println("Unit: " + exchangeData.getUnit());
            System.out.println("Value: " + exchangeData.getValue() + "\n");

        }

        System.out.println("Az app sikeresen elindult!");
    }

    //App entry point
    public static void main(String[] args) {
        //System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        //System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        //System.setProperty("com.sun.xml.ws.util.pipe.StandaloneTubeAssembler.dump", "true");

        launch();
    }
}