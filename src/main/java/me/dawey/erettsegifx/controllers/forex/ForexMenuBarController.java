package me.dawey.erettsegifx.controllers.forex;

import javafx.event.ActionEvent;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;

public class ForexMenuBarController {

    public void billing(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXBILLINFO, Navigator.getHomeController());
    }

    public void openedPos(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXOPENEDPOS, Navigator.getHomeController());
    }

    public void posClosed(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXPOSCLOSED, Navigator.getHomeController());
    }

    public void posOpen(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXPOSOPEN, Navigator.getHomeController());
    }

    public void actual(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXACTUAL, Navigator.getHomeController());
    }

    public void history(ActionEvent actionEvent) {
        Navigator.navigate(NavigationAction.FOREXHISTORY, Navigator.getHomeController());
    }

}
