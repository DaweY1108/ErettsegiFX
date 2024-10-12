package me.dawey.erettsegifx.controllers;

import javafx.event.ActionEvent;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;

public class ReadController2 {


    public void goToReadButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.READ, Navigator.getHomeController());
    }
}
