package com.example.samochodgui.symulator;

public class Silnik extends Komponent {
    int maxObroty = 6000;
    int obroty = 0;

    public void zwiekszObroty(){
        if(this.obroty < maxObroty){
            this.obroty+=250;
        }
    }

    public void zmniejszObroty(){
        if(this.obroty > 0){
            this.obroty-=250;
        }
    }

    public int getObroty(){ return obroty; }

    public void setObroty(int obroty){
        this.obroty = obroty;
    }

    public int getMaxObroty(){ return maxObroty; }

}
