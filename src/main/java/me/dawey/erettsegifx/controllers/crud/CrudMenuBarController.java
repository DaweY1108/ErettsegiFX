package me.dawey.erettsegifx.controllers.crud;

import javafx.event.ActionEvent;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;

public class CrudMenuBarController {

    public void selectCreateButtonAction(ActionEvent actionEvent)
    {
        Navigator.navigate(NavigationAction.CRUDCREATE, Navigator.getHomeController());
    }

    public void selectReadButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.CRUDREAD, Navigator.getHomeController());
    }

    public void selectUpdateButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.CRUDUPDATE, Navigator.getHomeController());
    }

    public void selectDeleteButtonAction(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.CRUDDELETE, Navigator.getHomeController());
    }
}
