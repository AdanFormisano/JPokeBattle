module com.example.jpokebattle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.jpokebattle to javafx.fxml;
    exports com.example.jpokebattle;
}