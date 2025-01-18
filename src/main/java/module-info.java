module com.example.jpokebattle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires org.jetbrains.annotations;

    opens com.example.jpokebattle to javafx.fxml;
    exports com.example.jpokebattle;

    opens com.example.jpokebattle.service.data to com.fasterxml.jackson.databind;
    exports com.example.jpokebattle.service.data;
}