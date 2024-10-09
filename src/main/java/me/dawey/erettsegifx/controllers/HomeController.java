package me.dawey.erettsegifx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> actionComboBox;

    @FXML
    private void minimizeWindow() {
        Stage stage = (Stage) topGridPane.getScene().getWindow();
        stage.setIconified(true);
    }

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
    public void createButtonAction(ActionEvent event) {
//        navigator.navigate(Navigator.CREATE);

    }
    public void readButtonAction(ActionEvent event) {
//        navigator.navigate(Navigator.READ);

    }
    public void updateButtonAction(ActionEvent event) {
//        navigator.navigate(Navigator.UPDATE);

    }
    public void deleteButtonAction(ActionEvent event) {
//        navigator.navigate(Navigator.DELETE);

    }


    public void handleComboBoxAction(ActionEvent actionEvent) {
        String selectedAction = actionComboBox.getValue();
        switch (selectedAction) {
            case "Create":
                navigator.navigate(Navigator.CREATE, containerBorderPane);
                break;
            case "Read":
                navigator.navigate(Navigator.READ, containerBorderPane);
                break;
            case "Read 2":
                navigator.navigate(Navigator.READ2, containerBorderPane);
                break;
            case "Update":
                navigator.navigate(Navigator.UPDATE, containerBorderPane);
                break;
            case "Delete":
                navigator.navigate(Navigator.DELETE, containerBorderPane);
                break;
        }
    }
}