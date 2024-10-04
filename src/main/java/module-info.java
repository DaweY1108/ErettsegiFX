module me.dawey.erettsegifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.dawey.erettsegifx to javafx.fxml;
    exports me.dawey.erettsegifx;
}