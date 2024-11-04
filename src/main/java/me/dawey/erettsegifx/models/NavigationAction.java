package me.dawey.erettsegifx.models;

public enum NavigationAction {
    CRUDCREATE("views/CRUD/create-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    CRUDREAD("views/CRUD/read-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    CRUDREAD2("views/CRUD/read-2-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    CRUDUPDATE("views/CRUD/update-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    CRUDDELETE("views/CRUD/delete-view.fxml", "views/CRUD/crud-menu-bar.fxml"),

    SOAPDOWNLOAD("views/SOAP/soap-download.fxml", "views/SOAP/soap-menu-bar.fxml"),
    SOAPDOWNLOAD2("views/SOAP/soap-download2.fxml", "views/SOAP/soap-menu-bar.fxml"),
    SOAPGRAPH("views/SOAP/soap-graph.fxml", "views/SOAP/soap-menu-bar.fxml"),

    THREADING("views/THREADING/threading-view.fxml", "views/THREADING/threading-menu-bar.fxml"),

    FOREXBILLINFO("views/FOREX/forex-billing-info.fxml", "views/FOREX/forex-menu-bar.fxml"),
    FOREXACTUAL("views/FOREX/forex-actual.fxml", "views/FOREX/forex-menu-bar.fxml"),
    FOREXHISTORY("views/FOREX/forex-history.fxml", "views/FOREX/forex-menu-bar.fxml"),
    FOREXPOSOPEN("views/FOREX/forex-posopen.fxml", "views/FOREX/forex-menu-bar.fxml"),
    FOREXPOSCLOSED("views/FOREX/forex-posclose.fxml", "views/FOREX/forex-menu-bar.fxml"),
    FOREXOPENEDPOS("views/FOREX/forex-openedpos.fxml", "views/FOREX/forex-menu-bar.fxml");
    private final String contentFxml;
    private final String menuBarFxml;

    NavigationAction(String contentFxml, String menuBarFxml) {
        this.contentFxml = contentFxml;
        this.menuBarFxml = menuBarFxml;
    }

    public String getContentFxml() {
        return contentFxml;
    }

    public String getMenuBarFxml() {
        return menuBarFxml;
    }
}
