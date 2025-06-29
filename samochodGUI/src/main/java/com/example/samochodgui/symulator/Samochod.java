package com.example.samochodgui.symulator;


import com.example.samochodgui.SamochodController;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;


public class Samochod extends Thread {
    private SamochodController controller;

    public void setController(SamochodController controller) {
        this.controller = controller;
    }

    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (Listener listener : listeners) {
            if (listener != null) {
                listener.update();
            }
        }
    }


    boolean stanWlaczenia = false;
    boolean stanDostepnosciSprzegla = false;
    String nrRejest = "SO BEAST";
    String model = "Toyota Prius";
    int predkoscMax = 350;
    double aktPredkosc = 0;
    double waga = 100;
    //
    Silnik silnik;
    SkrzyniaBiegow skrzyniaBiegow;
    Pozycja pozycja;
    Pozycja cel = null;

    public Samochod() {
        super();
        silnik = new Silnik();
        skrzyniaBiegow = new SkrzyniaBiegow();
        pozycja = new Pozycja(20, 20);
        start();
    }

    public Samochod(String model, String nrRejest, double waga, int predkoscMax) {
        super();
        this.model = model;
        this.nrRejest = nrRejest;
        this.waga = waga;
        this.predkoscMax = predkoscMax;
        //
        silnik = new Silnik();
        skrzyniaBiegow = new SkrzyniaBiegow();
        pozycja = new Pozycja(20, 20);
        start();
    }

    public SkrzyniaBiegow getSkrzyniaBiegow(){
        return skrzyniaBiegow;
    }

    public Silnik getSilnik(){
        return silnik;
    }

    public Pozycja getPozycja(){
        return pozycja;
    }

    public void wlacz(){
        stanWlaczenia = true;
    }

    public void wylacz(){
        stanWlaczenia = false;
    }

    public void jedzDo(Pozycja nowaPozycja){
        this.cel = nowaPozycja;
        System.out.println("Target required: " + cel.getX() + " " + cel.getY());
        if (controller != null) {
            Platform.runLater(controller::refresh);
            notifyListeners();
        }
    }

    public double getAktPredkosc(){
        return aktPredkosc;
    }

    public double getMaxPredkosc(){
        return predkoscMax;
    }

    public void setPredkosc(){
        this.aktPredkosc = (double) predkoscMax /silnik.getMaxObroty()* silnik.getObroty();
        System.out.println("Predkosc: " + String.format("%.0f",aktPredkosc));
    }

    public void setPredkosc(double predkosc){
        this.aktPredkosc = predkosc;
    }

    public int getPredkoscMax(){
        return predkoscMax;
    }

    public double getAktPozycja(){
        return pozycja.x;
    }

    public String getSamochodName(){
        return model;
    }

    public String toString() { return model; }

    public String getNrRejest(){
        return nrRejest;
    }

    public double getWaga(){
        return waga;
    }

    public boolean getStanWlaczenia(){ return stanWlaczenia; }

    public boolean getStanDostepnosciSprzegla() { return stanDostepnosciSprzegla; }

    public void setStanDostepnosciSprzegla(boolean b){this.stanDostepnosciSprzegla = b;}

    public void run() {
        System.out.println("Метод run() запущен.");

        double deltaT = 0.01;

        while (true) {
            if (cel != null) {
                double odleglosc = Math.sqrt(Math.pow(cel.x - pozycja.x, 2) + Math.pow(cel.y - pozycja.y, 2));

                if (odleglosc == 0) {
                    cel = null;
                    continue;
                }

                double dx = getAktPredkosc() * deltaT * (cel.x - pozycja.x) / odleglosc;
                double dy = getAktPredkosc() * deltaT * (cel.y - pozycja.y) / odleglosc;

                pozycja.x += dx;
                pozycja.y += dy;

                notifyListeners();
            }

            try {
                Thread.sleep(10); // Задержка на 100 мс
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Samochod samochod = new Samochod();
    }
}
