module com.example.jpokebattle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.jpokebattle to javafx.fxml;
    exports com.example.jpokebattle;

    opens com.example.jpokebattle.gui to javafx.fxml;
    exports com.example.jpokebattle.gui;

    opens com.example.jpokebattle.service.data to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service.data;

    opens com.example.jpokebattle.service.loader to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service.loader;

    opens com.example.jpokebattle.poke to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.poke;

    opens com.example.jpokebattle.service to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service;
}