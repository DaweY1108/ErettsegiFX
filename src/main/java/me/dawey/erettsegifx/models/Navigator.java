package me.dawey.erettsegifx.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import me.dawey.erettsegifx.Main;

import java.util.Objects;

public class Navigator {

    // Golden to Dave: A masik oldalon latszik majd, hogy miert igy csinaltam, nem ertek a javahoz ezert ez ilyen teszta, ahogy Csik Norbert ma mondta

    // Konstansok a navigatorhoz, hogy csak itt kelljen modositani a file neveket

    public static final String CREATE = "views/create-view.fxml";
    public static final String READ = "views/read-view.fxml";
    public static final String UPDATE = "views/update-view.fxml";
    public static final String DELETE = "views/delete-view.fxml";

    public void navigate(String fxml, BorderPane containerBorderPane) {
            try{
                // Ez megfogra a fxml-t, es betolti a containerBorderPane center-be
                containerBorderPane.getChildren().clear();
                containerBorderPane.setCenter(new FXMLLoader(Objects.requireNonNull(Main.class.getResource(fxml))).load());
            }
            catch (Exception e){

                e.printStackTrace();
            }


    }
}
