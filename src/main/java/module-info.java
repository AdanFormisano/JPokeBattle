module com.example.jpokebattle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires java.xml;

    opens com.example.jpokebattle to javafx.fxml;
    exports com.example.jpokebattle;

    exports com.example.jpokebattle.gui;

    opens com.example.jpokebattle.service.session;
    exports com.example.jpokebattle.service.session;

    opens com.example.jpokebattle.game;
    exports com.example.jpokebattle.game;

    opens com.example.jpokebattle.service.data to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service.data;

    opens com.example.jpokebattle.service.loader to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service.loader;

    exports com.example.jpokebattle.poke;

    opens com.example.jpokebattle.service to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service;
    opens com.example.jpokebattle.gui to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.jpokebattle.gui.views;
    opens com.example.jpokebattle.gui.views to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.jpokebattle.gui.data;
    opens com.example.jpokebattle.gui.data to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.jpokebattle.poke.move;
    opens com.example.jpokebattle.poke.move to com.fasterxml.jackson.databind;
    opens com.example.jpokebattle.poke;
}