package com.example.samochodgui.symulator;


public class Samochod {
    boolean stanWlaczenia = false;
    String nrRejest = "SO BEAST";
    String model = "Toyota Prius";
    int predkoscMax = 350;
    int aktPredkosc = 0;
    double waga = 0;

    Silnik silnik;
    SkrzyniaBiegow skrzyniaBiegow;
    Pozycja pozycja;

    public Samochod() {
        silnik = new Silnik();
        skrzyniaBiegow = new SkrzyniaBiegow();
        pozycja = new Pozycja(20, 20);
    }

    public Samochod(String model, String nrRejest, double waga, int predkoscMax) {
        silnik = new Silnik();
        skrzyniaBiegow = new SkrzyniaBiegow();
        pozycja = new Pozycja(20, 20);
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

    public void jedzDo(int x, int y){
        pozycja.x = x;
        pozycja.y = y;
    }

    public double getAktPredkosc(){
        return aktPredkosc;
    }

    public double getPredkoscMax(){
        return predkoscMax;
    }

    public double getAktPozycja(){
        return pozycja.x;
    }

    public String getName(){
        return model;
    }

    public String toString() { return model; }

    public String getNrRejest(){
        return nrRejest;
    }

    public double getWaga(){
        return waga;
    }

    public static void main(String[] args) {
        Samochod samochod = new Samochod();

    }
}
