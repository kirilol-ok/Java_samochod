package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DodajSamochodController {

    private HelloController controller;

    public void setHelloController(HelloController controller){
        this.controller = controller;
        System.out.println("Controller received: " + controller);
    }

    @FXML
    private TextField modelTextField;
    @FXML
    private TextField registrationTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField speedTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;



    @FXML
    private void addCarToList() {
        String model = modelTextField.getText();
        String nrRejest = registrationTextField.getText();
        double waga;
        int predkosc;

        try {
            waga = Double.parseDouble(weightTextField.getText());
            predkosc = Integer.parseInt(speedTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Niepoprawne dane. Spróbuj ponownie.");
            return;
        }

        System.out.println("Adding car: " + model + ", " + nrRejest + ", " + waga + ", " + predkosc);

        if (controller != null) {
            controller.addCarToList(model, nrRejest, waga, predkosc);
            controller.enable();
        } else {
            System.out.println("Controller is null. Cannot add car.");
            return;
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
