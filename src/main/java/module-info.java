module me.dawey.erettsegifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires java.sql;
    requires jakarta.xml.ws;


    opens me.dawey.erettsegifx to javafx.fxml, com.sun.xml.bind, jakarta.xml.bind;
    opens me.dawey.erettsegifx.models.mnbank.data to com.sun.xml.bind, jakarta.xml.bind;
    exports me.dawey.erettsegifx;
    exports me.dawey.erettsegifx.controllers;
    exports me.dawey.erettsegifx.models.database.tables;
    exports me.dawey.erettsegifx.models.database;
    opens me.dawey.erettsegifx.controllers to javafx.fxml;
    opens me.dawey.erettsegifx.models.database.tables to ormlite.jdbc;
    opens soap to com.sun.xml.bind, com.sun.xml.ws, jakarta.xml.bind;

}