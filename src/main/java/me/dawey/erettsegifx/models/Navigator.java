package me.dawey.erettsegifx.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import me.dawey.erettsegifx.Main;
import me.dawey.erettsegifx.controllers.HomeController;

import java.util.Objects;

public class Navigator {

    public static Navigator instance = new Navigator();
    public static HomeController setHomeController;
    public static HomeController getHomeController() {
        return setHomeController;
    }
    public static void setHomeController(HomeController homeController) {
        setHomeController = homeController;
    }

    public static void navigate(NavigationAction navaction, HomeController homeController) {
            try{
                // Ez megfogra a content_fxml-t, es betolti a containerBorderPane center-be
                homeController.getContainerBorderPane().getChildren().clear();
                homeController.getContainerBorderPane().setCenter(new FXMLLoader(Objects.requireNonNull(Main.class.getResource(navaction.getContentFxml()))).load());

                homeController.getMenuBarContainer().getChildren().clear();
                homeController.getMenuBarContainer().getChildren().add(new FXMLLoader(Objects.requireNonNull(Main.class.getResource(navaction.getMenuBarFxml()))).load());
            }
            catch (Exception e){

                e.printStackTrace();
            }


    }
}
