package com.example.samochodgui.symulator;

public class Silnik extends Komponent {
    int maxObroty = 6100;
    int obroty = 0;

    public void zwiekszObroty(){
        if(this.obroty < maxObroty){
            this.obroty+=305;
        }
    }

    public void zmniejszObroty(){
        if(this.obroty > 0){
            this.obroty-=305;
        }
    }

    public int getObroty(){ return obroty; }

    public int getMaxObroty(){ return maxObroty; }

}
