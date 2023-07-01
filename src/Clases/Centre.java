package Clases;

import java.util.ArrayList;

public class Centre implements Comparable<Centre> {

    private final String nom;
    private final ArrayList<Barco> barcos;

    public Centre(String nom) {
        this.nom = nom;
        this.barcos = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void addBarco(Barco barco) {
        barcos.add(barco);
    }


    public boolean teTipus(String tipus){
        for(Barco barco: barcos){
            if(barco.getTipus().equals(tipus)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Centre o) {
        return Integer.compare(this.barcos.size(), o.barcos.size());
    }
}
