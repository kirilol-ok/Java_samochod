package symulator;

public class SkrzyniaBiegow extends Komponent {
    int aktualnyBieg = 1;
    int iloscBiegow = 6;
    int aktualnePrzelozenie = 0;

    Sprzeglo sprzeg;

    public SkrzyniaBiegow() {
        sprzeg = new Sprzeglo();
    }

    public void zwiekszBieg() {
        sprzeg.wcisnij();
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
        else {
            aktualnyBieg = 6;
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
