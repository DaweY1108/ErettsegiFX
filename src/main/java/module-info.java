module me.dawey.erettsegifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires java.sql;
    requires org.slf4j;
    requires jakarta.xml.ws;


    opens me.dawey.erettsegifx to javafx.fxml, com.sun.xml.bind;
    exports me.dawey.erettsegifx;
    exports me.dawey.erettsegifx.controllers;
    exports me.dawey.erettsegifx.database.tables;
    exports me.dawey.erettsegifx.database;
    opens me.dawey.erettsegifx.controllers to javafx.fxml;
    opens me.dawey.erettsegifx.database.tables to ormlite.jdbc;
    opens soap to com.sun.xml.bind, com.sun.xml.ws;

}