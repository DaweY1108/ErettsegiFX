package me.dawey.erettsegifx.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HomeController {
    private double xOffset = 0;
    private double yOffset = 0;

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
}