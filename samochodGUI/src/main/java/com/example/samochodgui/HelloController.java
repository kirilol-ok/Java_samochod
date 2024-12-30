package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.ErrorManager;

import javafx.util.StringConverter;

public class HelloController {
    private Samochod samochod;
    private HelloController controller;

    private void setHelloController(HelloController controller){
        this.controller = controller;
    }

    @FXML
    private ChoiceBox<Samochod> samochodyChoiceBox;

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
    private TextField aktPredkosc;

    @FXML
    private Rectangle stanWlaczenia;

    @FXML
    private ImageView carImageView;

    private ObservableList<Samochod> samochody = FXCollections.observableArrayList(
            samochod = new Samochod()
    );

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");
        samochodyChoiceBox.setItems(samochody);

        samochodyChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Samochod samochod) {
                return samochod != null ? samochod.getName() : "";
            }

            @Override
            public Samochod fromString(String string) {
                return null; // Этот метод не используется
            }
        });

        samochodyChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                samochod = newValue; // Устанавливаем текущий выбранный автомобиль
                refresh(); // Обновляем поля
            }
        });

        if (!samochody.isEmpty()) {
            samochodyChoiceBox.setValue(samochody.get(0));
        }
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
    void refresh() {
        if (samochod != null) {
            samochodNazwa.setText(samochod.getName());
            nrRejestracyjny.setText(samochod.getNrRejest());
            predkosc.setText(String.valueOf(samochod.getAktPredkosc()));
            bieg.setText(String.valueOf(samochod.getSkrzyniaBiegow().getAktBieg()));
            obroty.setText(String.valueOf(samochod.getSilnik().getObroty()));
            stanWlaczenia.setVisible(samochod.getStanWlaczenia());
            aktPredkosc.setText(String.valueOf(samochod.getAktPredkosc()));
        } else {
            samochodNazwa.clear();
            nrRejestracyjny.clear();
            predkosc.clear();
            bieg.clear();
            obroty.clear();
            stanWlaczenia.setVisible(false);
            aktPredkosc.clear();
        }
    }

    public void openAddCarWindow() throws IOException {
        System.out.println("DodajSamochodController initialized");

        // Загружаем FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Parent root = loader.load();
        DodajSamochodController controller = loader.getController();
        controller.setHelloController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();
        refresh();
    }

    public void addCarToList(String model, String nrRejest, double waga, int predkosc) {
        Samochod samochod = new Samochod(model, nrRejest, waga, predkosc);
        samochody.add(samochod);
        samochodyChoiceBox.setValue(samochod);
        refresh();
    }

    @FXML
    public void onUsun() {
        // Получаем выбранный автомобиль
        Samochod selectedCar = samochodyChoiceBox.getValue();

        if (selectedCar != null) {
            samochody.remove(selectedCar);

            if (!samochody.isEmpty()) {
                samochodyChoiceBox.setValue(samochody.get(0));
            } else {
                samochodyChoiceBox.setValue(null);
            }

            refresh();
            System.out.println("Samochod usuniety: " + selectedCar.getName());
        } else {
            System.out.println("Nie wybrano samochodu do usunięcia.");
        }
    }


    @FXML
    private void onDodajGazu() {
        int aktMaxObroty = samochod.getSilnik().getMaxObroty()
                /samochod.getSkrzyniaBiegow().getIloscBiegow()
                *samochod.getSkrzyniaBiegow().getAktBieg();
        if(samochod.getSilnik().getObroty() >= aktMaxObroty){
            System.out.println("Zwieksz bieg!!!");
        } else {
            samochod.getSilnik().zwiekszObroty();
        }
        samochod.setPredkosc();
        System.out.println(samochod.getAktPredkosc());
        refresh();
    }
    @FXML
    private void onUjmijGazu() {
        samochod.getSilnik().zmniejszObroty();
        samochod.setPredkosc();
        refresh();
    }
    @FXML
    private void onWlancz(){
        samochod.wlacz();
        stanWlaczenia.setVisible(true);
        System.out.println("Samochod uruchomiony!");
        refresh();

    }
    @FXML
    private void onWylancz(){
        samochod.wylacz();
        stanWlaczenia.setVisible(false);
        System.out.println("Samochod kaput");
        refresh();
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

