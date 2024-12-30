module com.example.samochodgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.example.samochodgui to javafx.fxml;
    exports com.example.samochodgui;
}