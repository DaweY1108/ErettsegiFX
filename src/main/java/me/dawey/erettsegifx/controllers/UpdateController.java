package me.dawey.erettsegifx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.dawey.erettsegifx.models.Navigator;

public class UpdateController {
    private double xOffset = 0;
    private double yOffset = 0;
    private final Navigator navigator = new Navigator();
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


    public void createButtonAction(ActionEvent actionEvent) {
//        navigator.navigate(Navigator.CREATE);
        navigator.navigate("create-view.fxml", topGridPane);

    }
    public void readButtonAction(ActionEvent actionEvent) {
//        navigator.navigate(Navigator.READ);
        navigator.navigate("read-view.fxml", topGridPane);

    }
    public void updateButtonAction(ActionEvent actionEvent) {
//        navigator.navigate(Navigator.UPDATE);
        navigator.navigate("update-view.fxml", topGridPane);

    }
    public void deleteButtonAction(ActionEvent actionEvent) {
//        navigator.navigate(Navigator.DELETE);
        navigator.navigate("delete-view.fxml", topGridPane);

    }

}
