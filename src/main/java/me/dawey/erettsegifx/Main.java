package me.dawey.erettsegifx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.dawey.erettsegifx.database.Database;
import me.dawey.erettsegifx.database.tables.Vizsgazo;

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


        System.out.println("Az app sikeresen elindult!");
    }

    //App entry point
    public static void main(String[] args) {
        launch();
    }
}