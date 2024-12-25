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

    public void MainController(HelloController controller){
        this.controller = controller;
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

//    @FXML
//    private void onConfirmButton() {
//        String model = modelTextField.getText();
//        String registration = registrationTextField.getText();
//        double weight;
//        int speed;
//        try {
//            weight = Double.parseDouble(weightTextField.getText());
//            speed = Integer.parseInt(speedTextField.getText());
//        } catch (NumberFormatException e) {
//            System.out.println("Niepoprawne dane. Spróbuj ponownie.");
//            return;
//        }
//        HelloController.addCarToList(model, registration, weight, speed);
//        Stage stage = (Stage) confirmButton.getScene().getWindow();
//        stage.close();
//    }
//
//    @FXML
//    private void onCancelButton() {
//        Stage stage = (Stage) cancelButton.getScene().getWindow();
//        stage.close();
//    }
}
