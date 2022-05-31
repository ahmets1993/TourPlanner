module com.example.tourplanner {
    requires java.naming;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires pgjdbc.ng;
    requires org.postgresql.jdbc;
    requires java.net.http;
    requires org.json;
    requires java.desktop;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires jdk.jsobject;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires  layout;
    requires io;
    requires kernel;

    opens com.example.tourplanner.View;


    exports com.example.tourplanner;
    exports com.example.tourplanner.Model;
    exports com.example.tourplanner.View;
    exports com.example.tourplanner.ViewModel;

}