module me.dawey.erettsegifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens me.dawey.erettsegifx to javafx.fxml;
    exports me.dawey.erettsegifx;
    exports me.dawey.erettsegifx.controllers;
    opens me.dawey.erettsegifx.controllers to javafx.fxml;
}