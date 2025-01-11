package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajSamochodController {

    private SamochodController controller;

    public void setHelloController(SamochodController controller){
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
