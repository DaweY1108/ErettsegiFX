package me.dawey.erettsegifx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;

public class CrudMenuBarController {

    public void selectCreateButtonAction(ActionEvent actionEvent)
    {
        Navigator.navigate(NavigationAction.CREATE, Navigator.getHomeController());
    }

    public void selectReadButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.READ, Navigator.getHomeController());
    }

    public void selectUpdateButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.UPDATE, Navigator.getHomeController());
    }

    public void selectDeleteButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.DELETE, Navigator.getHomeController());
    }
}
