package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        HelloController controller = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        stage.setTitle("Symulator samochodu");
        stage.setScene(scene);
        stage.show();

        Samochod samochod = new Samochod();
        samochod.changeState();

        URL imageUrl = getClass().getResource("/images/car_background.jpg");
        if (imageUrl == null) {
            System.out.println("Image not found!");
        } else {
            System.out.println("Image found: " + imageUrl);
        }

    }


    public static void main(String[] args) {
        launch();
    }
}