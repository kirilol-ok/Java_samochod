package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class HelloController {
    private Samochod samochod;

    @FXML
    private ChoiceBox samochodyChoiceBox;

    @FXML
    private Button engineStart;
    @FXML
    private Button engineStop;
    @FXML
    private Button zwiekszBieg;
    @FXML
    private Button zmniejszBieg;
    @FXML
    private Button nacisnij;
    @FXML
    private Button zwolnij;
    @FXML
    private Button dodajGazu;
    @FXML
    private Button ujmijGazu;
    @FXML
    private Button dodajSamochod;
    @FXML
    private Button usunSamochod;

    @FXML
    private TextField nrRejestracyjny;
    @FXML
    private TextField predkosc;
    @FXML
    private TextField samochodNazwa;
    @FXML
    private TextField samochodWaga;
    @FXML
    private TextField bieg;
    @FXML
    private TextField obroty;

    @FXML
    private ImageView carImageView;

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");

        samochod = new Samochod();

        //
        // Load and set the car image
        Image carImage = new Image(getClass().getResource("/images/car.png").toExternalForm());
        System.out.println("Image width: " + carImage.getWidth() + ", height: " + carImage.getHeight());
        carImageView.setImage(carImage);
        carImageView.setFitWidth(80); // Set appropriate
        carImageView.setFitHeight(48);
        carImageView.setTranslateX(20);
        carImageView.setTranslateY(20);
        refresh();
    }

    @FXML
    void refresh(){
        samochodNazwa.setText(samochod.getName());
        nrRejestracyjny.setText(samochod.getNrRejest());
        predkosc.setText(String.valueOf(samochod.getAktPredkosc()));
        bieg.setText(String.valueOf(samochod.getSkrzyniaBiegow().getAktBieg()));
        obroty.setText(String.valueOf(samochod.getSilnik().getObroty()));
    }

    public void openAddCarWindow() throws IOException {
        System.out.println("DodajSamochodController initialized");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }


    public void onUsun(ActionEvent actionEvent) {
        System.out.println("Samochod usuniety");
    }

    @FXML
    private void onDodajGazu() {
        samochod.getSilnik().zwiekszObroty();
        refresh();
    }
    @FXML
    private void onUjmijGazu() {
        samochod.getSilnik().zmniejszObroty();
        refresh();
    }
    @FXML
    private void onWlancz(){
        samochod.wlacz();
        System.out.println("Samochod uruchomiony!");

    }
    @FXML
    private void onWylancz(){
        samochod.wylacz();
        System.out.println("Samochod kaput");
    }
    @FXML
    private void onZwiekszBieg(){
        samochod.getSkrzyniaBiegow().zwiekszBieg();
        refresh();
    }
    @FXML
    private void onZmniejszBieg(){
        samochod.getSkrzyniaBiegow().zmniejszBieg();
        refresh();
    }
    @FXML
    private void onNacisnij(){
        samochod.getSkrzyniaBiegow().getSprzeglo().wcisnij();
    }
    @FXML
    private void onZwolnij(){
        samochod.getSkrzyniaBiegow().getSprzeglo().zwolnij();
    }

}

