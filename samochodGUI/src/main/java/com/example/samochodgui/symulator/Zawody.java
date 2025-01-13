package com.example.samochodgui.symulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zawody {
    private String nazwa;
    private Date data;
    private List<Samochod> uczestnicy;

    // Конструктор
    public Zawody(String nazwa, Date data) {
        this.nazwa = nazwa;
        this.data = data;
    }

    // Добавление участника
    public void dodajUczestnika(Samochod samochod) {
        if (uczestnicy == null) {
            uczestnicy = new ArrayList<>();
        }
        uczestnicy.add(samochod);
        System.out.println("Uczestnik dodany: " + samochod.getSamochodName());
    }


    // Удаление участника
    public void usunUczestnika(Samochod samochod) {
        uczestnicy.remove(samochod);
    }

    // Проведение соревнований
    public Samochod rozegrajZawody() {
        if (uczestnicy.isEmpty()) {
            System.out.println("Nie ma uczwstnikow dla zawodow");
            return null;
        }

        System.out.println("Zawidy sie rospoczeli! Uczestnicy: ");
        for (Samochod car : uczestnicy) {
            System.out.println("Samochod: " + car.getSamochodName() + ", Predkosc Maksymalna: " + car.getMaxPredkosc() + " км/ч");
        }

        Samochod winner = null;
        double maxSpeed = 0;

        // Определяем победителя
        for (Samochod car : uczestnicy) {
            if (car.getMaxPredkosc() > maxSpeed) {
                maxSpeed = car.getMaxPredkosc();
                winner = car;
            }
        }

        if (winner != null) {
            System.out.println("Zwyciezca: " + winner.getSamochodName() + " z predkoscia maksymalna: " + maxSpeed + "km/g");
        }

        return winner;
    }
}

