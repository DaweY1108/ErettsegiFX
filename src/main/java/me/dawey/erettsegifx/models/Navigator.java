package me.dawey.erettsegifx.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.dawey.erettsegifx.Main;

import java.io.IOException;
import java.util.Objects;

public class Navigator {

    // Golden to Dave: A masik oldalon latszik majd, hogy miert igy csinaltam, nem ertek a javahoz ezert ez ilyen teszta, ahogy Csik Norbert ma mondta

    public static final String CREATE = "create-view.fxml";
    public static final String READ = "read-view.fxml";
    public static final String UPDATE = "update-view.fxml";
    public static final String DELETE = "delete-view.fxml";
    public static final String HOME = "home-view.fxml";

    public void navigate(String fxml, GridPane topGridPane) {

        try {
            Stage stage = (Stage) topGridPane.getScene().getWindow();
            stage.setTitle("ErettsegiFX");
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml))), 800, 600));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
