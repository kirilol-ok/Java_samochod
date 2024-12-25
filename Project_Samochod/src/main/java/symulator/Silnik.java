package symulator;

public class Silnik extends Komponent {
    int maxObroty = 6100;
    int obroty = 0;

    public void wlacz() {

    }

    public void wylacz() {

    }

    public void zwiększObroty(){
        if(this.obroty < 4000){
            this.obroty+=250;
        }
    }

    public void zmniejszObroty(){
        if(this.obroty > 0){
            this.obroty-=250;
        }
    }

}
