package symulator;

public class Sprzeglo extends Komponent{
    boolean stanSprzegla = false;

    public void wcisnij(){
        this.stanSprzegla = true;
    }

    public void zwolnij(){
        this.stanSprzegla = false;
    }
}
