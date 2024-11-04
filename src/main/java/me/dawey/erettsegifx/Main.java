package me.dawey.erettsegifx;

import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.DateTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.dawey.erettsegifx.models.database.Database;
import me.dawey.erettsegifx.models.oanda.Oanda;

import java.io.IOException;

public class Main extends Application {
    public static Database database;
    //Start the application
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ErettsegiFX");
        stage.setScene(scene);
        stage.show();
        database = new Database();
        Oanda oanda = new Oanda();
        InstrumentCandlesResponse candles = oanda.getHistory("EUR_USD");
        for (Candlestick candle : candles.getCandles()) {
            System.out.println(candle.getTime() + " " + candle.getMid().getC());
        }
    }

    //App entry point
    public static void main(String[] args) {
        Logger.setGlobalLogLevel(Level.ERROR);
        launch();
    }


}