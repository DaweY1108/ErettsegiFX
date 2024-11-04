package me.dawey.erettsegifx.controllers.soap;

import javafx.event.ActionEvent;
import me.dawey.erettsegifx.models.NavigationAction;
import me.dawey.erettsegifx.models.Navigator;

public class SoapMenuBarController {
    public void downloadAction(ActionEvent event) {
        Navigator.navigate(NavigationAction.SOAPDOWNLOAD, Navigator.getHomeController());
    }
    public void download2Action(ActionEvent event) {
        Navigator.navigate(NavigationAction.SOAPDOWNLOAD2, Navigator.getHomeController());
    }
    public void graphAction(ActionEvent event) {
        Navigator.navigate(NavigationAction.SOAPGRAPH, Navigator.getHomeController());
    }
}
