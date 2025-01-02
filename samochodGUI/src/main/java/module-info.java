module com.example.samochodgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.net.http;

    opens com.example.samochodgui to javafx.fxml;
    exports com.example.samochodgui;
}