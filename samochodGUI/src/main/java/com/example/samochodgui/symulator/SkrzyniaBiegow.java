package com.example.samochodgui.symulator;

public class SkrzyniaBiegow extends Komponent {
    int aktualnyBieg = 1;
    int iloscBiegow = 6;
    int aktualnePrzelozenie = 0;

    Sprzeglo sprzeglo;

    public SkrzyniaBiegow() {
        sprzeglo = new Sprzeglo();
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    public void zwiekszBieg() {
        sprzeglo.wcisnij();
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
        else {
            aktualnyBieg = iloscBiegow;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 1){
            aktualnyBieg--;
        }
        else {
            aktualnyBieg = 1;
        }
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public int getAktPrzelozenie() {
        return aktualnePrzelozenie;
    }

}
