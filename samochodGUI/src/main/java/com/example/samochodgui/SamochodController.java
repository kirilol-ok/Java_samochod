package com.example.samochodgui;

import com.example.samochodgui.symulator.Samochod;
import com.example.samochodgui.symulator.Pozycja;
import com.example.samochodgui.symulator.Zawody;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Date;

import javafx.util.StringConverter;
import javafx.scene.input.MouseEvent;


public class SamochodController {
    private Samochod samochod;
    private SamochodController controller;

    private void setHelloController(SamochodController controller){
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
    private Button startRace;

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
    private Circle stanSprzegla;

    @FXML
    private ImageView carImageView;

    @FXML
    private BorderPane mapa;

    private ObservableList<Samochod> samochody = FXCollections.observableArrayList(
            samochod = new Samochod()
    );

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");
        samochody.forEach(samochod -> samochod.setController(this));
        samochodyChoiceBox.setItems(samochody);

        samochodyChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Samochod samochod) {
                return samochod != null ? samochod.getSamochodName() : "";
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
        //
        mapa.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            Pozycja nowaPozycja = new Pozycja(x, y);
            samochod.jedzDo(nowaPozycja);
        });
        refresh();
    }


    @FXML
    public void refresh() {
        if (samochod != null) {
            Platform.runLater(() -> {
                if (samochod.getPozycja() != null) {
                    carImageView.setTranslateX(samochod.getPozycja().getX());
                    carImageView.setTranslateY(samochod.getPozycja().getY());
                }
            });

            samochodNazwa.setText(samochod.getSamochodName());
            nrRejestracyjny.setText(samochod.getNrRejest());
            predkosc.setText(String.format("%.0f", samochod.getAktPredkosc()));
            bieg.setText(String.valueOf(samochod.getSkrzyniaBiegow().getAktBieg()));
            obroty.setText(String.valueOf(samochod.getSilnik().getObroty()));
            stanWlaczenia.setVisible(samochod.getStanWlaczenia());
            aktPredkosc.setText(String.format("%.0f", samochod.getAktPredkosc()));
            samochodWaga.setText(String.valueOf(samochod.getWaga()));
        } else {
            samochodNazwa.clear();
            nrRejestracyjny.clear();
            predkosc.clear();
            bieg.clear();
            obroty.clear();
            aktPredkosc.clear();
            stanWlaczenia.setVisible(false);
        }
    }

    public void clear(){
        System.out.println("clearing fields...");
        samochod.getPozycja().setX(0);
        samochod.getPozycja().setY(0);
        samochodNazwa.setText("");
        nrRejestracyjny.setText("");
        predkosc.setText("");
        bieg.setText("");
        obroty.setText("");
        samochodWaga.setText("");
        stanWlaczenia.setVisible(false);
        aktPredkosc.setText("");
        onWylancz();
    }

    public void enable(){
        dodajGazu.setDisable(false);
        ujmijGazu.setDisable(false);
        zwiekszBieg.setDisable(false);
        zmniejszBieg.setDisable(false);
        engineStart.setDisable(false);
        engineStop.setDisable(false);
    }

    public void disable(){
        dodajGazu.setDisable(true);
        ujmijGazu.setDisable(true);
        zwiekszBieg.setDisable(true);
        zmniejszBieg.setDisable(true);
        engineStart.setDisable(true);
        engineStop.setDisable(true);
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
        samochod.setController(this);
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
                samochod = samochody.get(0); // Обновляем текущий автомобиль
            } else {
                clear();
                disable();
                samochodyChoiceBox.setValue(null);
                samochod = null; // Сбрасываем объект
            }
            refresh();
            System.out.println("Samochod usuniety: " + selectedCar.getSamochodName());
            pokazBlad("Samochod usuniety: " + selectedCar.getSamochodName());
        } else {
            System.out.println("Nie wybrano samochodu do usunięcia.");
            pokazBlad("Nie wybrano samochodu do usunięcia.");
        }
    }

    @FXML
    public void setOnMouseClicked(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        Pozycja nowaPozycja = new Pozycja(x, y);
        samochod.jedzDo(nowaPozycja);

        refresh();
    }



    @FXML
    private void onDodajGazu() {
        int aktObroty = samochod.getSilnik().getObroty();
        int aktMaxObroty = samochod.getSilnik().getMaxObroty()
                /samochod.getSkrzyniaBiegow().getIloscBiegow()
                *samochod.getSkrzyniaBiegow().getAktBieg();

        if(!samochod.getStanWlaczenia()) {
            System.out.println("Wlacz samochod!");
            pokazBlad("Wlacz samochod!");
        } else{
            if(aktObroty + 250 > aktMaxObroty){
                System.out.println("Zwieksz bieg!!!");
                pokazBlad("Zwieksz bieg!!!");
                samochod.getSilnik().setObroty(aktMaxObroty);
            } else {
                samochod.getSilnik().zwiekszObroty();
            }
            samochod.setPredkosc();
        }
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
        refresh();

    }
    @FXML
    private void onWylancz(){
        samochod.wylacz();
        onZwolnij();
        stanWlaczenia.setVisible(false);
        samochod.getSilnik().setObroty(0);
        obroty.setText("0");
        samochod.setPredkosc(0);
        predkosc.setText("0");
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
        int aktMaxObroty = samochod.getSilnik().getMaxObroty()
                /samochod.getSkrzyniaBiegow().getIloscBiegow()
                *samochod.getSkrzyniaBiegow().getAktBieg();
        if(samochod.getSilnik().getObroty() > aktMaxObroty){
            samochod.getSilnik().setObroty(aktMaxObroty);
            obroty.setText(String.valueOf(aktMaxObroty));
            samochod.setPredkosc();
        }
        refresh();
    }
    @FXML
    private void onNacisnij(){
        samochod.getSkrzyniaBiegow().getSprzeglo().wcisnij();
        stanSprzegla.setVisible(true);

        zwiekszBieg.setDisable(true);
        zmniejszBieg.setDisable(true);
        dodajGazu.setDisable(true);
        ujmijGazu.setDisable(true);
    }
    @FXML
    private void onZwolnij(){
        samochod.getSkrzyniaBiegow().getSprzeglo().zwolnij();
        stanSprzegla.setVisible(false);

        zwiekszBieg.setDisable(false);
        zmniejszBieg.setDisable(false);
        dodajGazu.setDisable(false);
        ujmijGazu.setDisable(false);
    }

    @FXML
    public void onStartRace() {
        if (samochody.isEmpty()) {
            pokazBlad("Nie ma samochodow dla zawodow");
            return;
        }

        // Создаём объект гонок
        Zawody zawody = new Zawody("Zawody na predkosc", new Date());
        for (Samochod car : samochody) {
            zawody.dodajUczestnika(car);
        }

        // Проводим гонку
        Samochod winner = zawody.rozegrajZawody();

        // Отображаем результаты
        if (winner != null) {
            pokazBlad("Zwyciezca: " + winner.getSamochodName() + " z predkoscia maksymalna: " + winner.getMaxPredkosc() + " km/g.");
        } else {
            pokazBlad("Nie udalo sie wyznaczyc zwyciezce");
        }
    }



    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }

}
