module com.example.databaseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.medusa;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.sql;

    opens com.example.databaseapp to javafx.fxml;
    exports com.example.databaseapp;
    exports com.example.databaseapp.controllers;
    opens com.example.databaseapp.controllers to javafx.fxml;
}