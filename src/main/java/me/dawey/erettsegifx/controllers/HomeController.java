package me.dawey.erettsegifx.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.dawey.erettsegifx.models.Navigator;


public class HomeController {
    private double xOffset = 0;
    private double yOffset = 0;
    private final Navigator navigator = new Navigator();

    @FXML
    private BorderPane containerBorderPane;

    @FXML
    private GridPane topGridPane;

    @FXML
    private void minimizeWindow() {
        Stage stage = (Stage) topGridPane.getScene().getWindow();
        stage.setIconified(true);
    }

    // Method to exit the application
    @FXML
    private void exitApplication() {
        Stage stage = (Stage) topGridPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        makeDraggable(topGridPane);
    }

    private void makeDraggable(GridPane node) {
        node.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        node.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    // Golden to Dave: Ezert kell a spagetti code amott, ha meg nem lattad, majd sirni fogsz
    //             ui: Sikerult spagetti nelkul
    public void createButtonAction() {
//        navigator.navigate(Navigator.CREATE);
        navigator.navigate(Navigator.CREATE, containerBorderPane);

    }
    public void readButtonAction() {
//        navigator.navigate(Navigator.READ);
        navigator.navigate(Navigator.READ, containerBorderPane);

    }
    public void updateButtonAction() {
//        navigator.navigate(Navigator.UPDATE);
        navigator.navigate(Navigator.UPDATE, containerBorderPane);

    }
    public void deleteButtonAction() {
//        navigator.navigate(Navigator.DELETE);
        navigator.navigate(Navigator.DELETE, containerBorderPane);

    }


}