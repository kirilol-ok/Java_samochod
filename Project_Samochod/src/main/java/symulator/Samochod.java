package symulator;
import java.util.ArrayList;


public class Samochod {
    boolean stanWlaczenia = false;
    String nrRejest = "SO BEAST";
    String model = "Toyota Prius";
    int predkoscMax = 350;
    int aktPredkosc = 0;

    Silnik silnik;
    SkrzyniaBiegow skrz;
    Pozycja pozycja;

    public Samochod() {
        silnik = new Silnik();
        skrz = new SkrzyniaBiegow();
        pozycja = new Pozycja(0, 0);
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

    public double getAktPozycja(){
        return pozycja.x;
    }

    public static void main(String[] args) {
        Samochod samochod = new Samochod();

    }
}
