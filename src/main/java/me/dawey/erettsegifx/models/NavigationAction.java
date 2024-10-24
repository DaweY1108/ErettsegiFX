package me.dawey.erettsegifx.models;

public enum NavigationAction {
    CREATE("views/CRUD/create-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    READ("views/CRUD/read-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    UPDATE("views/CRUD/update-view.fxml", "views/CRUD/crud-menu-bar.fxml"),
    DELETE("views/CRUD/delete-view.fxml", "views/CRUD/crud-menu-bar.fxml"),

    SOAP("views/SOAP/soap-view.fxml", "views/SOAP/soap-menu-bar.fxml"),
    FOREX("views/FOREX/forex-view.fxml", "views/FOREX/forex-menu-bar.fxml"),
    THREADING("views/THREADING/threading-view.fxml", "views/THREADING/threading-menu-bar.fxml");
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
