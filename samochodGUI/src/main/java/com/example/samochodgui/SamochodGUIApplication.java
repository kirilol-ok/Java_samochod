package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;

import javafx.geometry.Rectangle2D;


public class SamochodGUIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SamochodGUIApplication.class.getResource("SamochodApp.fxml"));
        SamochodController controller = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());

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