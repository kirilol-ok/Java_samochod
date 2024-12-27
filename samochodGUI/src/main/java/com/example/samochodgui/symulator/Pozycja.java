package com.example.samochodgui.symulator;

public class Pozycja{
    int x;
    int y;

    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPozycjaX(int x) {
        this.x = x;
        return x;
    }

    public int getPozycjaY(int y) {
        this.y = y;
        return y;
    }
}
