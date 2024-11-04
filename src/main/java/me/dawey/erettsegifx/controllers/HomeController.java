package me.dawey.erettsegifx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;


public class HomeController {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public HBox menuBarContainer;

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
        Navigator.setHomeController(this);
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

    public HBox getMenuBarContainer() {
        return menuBarContainer;
    }
    public BorderPane getContainerBorderPane() {
        return containerBorderPane;
    }

    public void handleComboBoxAction(ActionEvent actionEvent) {
        String selectedAction = actionComboBox.getValue();
        switch (selectedAction) {
            case "CRUD":
                Navigator.instance.navigate(NavigationAction.CRUDCREATE, this);
                break;
            case "SOAP":
                Navigator.instance.navigate(NavigationAction.SOAPDOWNLOAD, this);
                break;

            case "FOREX":
                Navigator.instance.navigate(NavigationAction.FOREXBILLINFO, this);
                break;
            case "THREADING":
                Navigator.instance.navigate(NavigationAction.THREADING, this);
                break;

        }
    }
}