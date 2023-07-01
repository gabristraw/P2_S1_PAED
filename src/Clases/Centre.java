package Clases;

import java.util.ArrayList;

public class Centre implements Comparable<Centre> {

    private final String nom;
    private final ArrayList<Barco> barcos;
    private  ArrayList<String> tipus;

    public Centre(String nom) {
        this.nom = nom;
        this.barcos = new ArrayList<>();
    }
    public Centre(String nom,int i) {
        this.nom = nom;
        this.barcos = new ArrayList<>();
        this.tipus = new ArrayList<>();
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

    public void addBarcoBack(Barco barco){
        barcos.add(barco);
        if(!tipus.contains(barco.getTipus())){
            tipus.add(barco.getTipus());
        }
    }
    public boolean teTipus(String tipus){
        for(Barco barco: barcos){
            if(barco.getTipus().equals(tipus)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getTipus() {
        return tipus;
    }

    @Override
    public int compareTo(Centre o) {
        return Integer.compare(this.barcos.size(), o.barcos.size());
    }

    public ArrayList<Integer> getTipoFaltante(ArrayList<String> tipos) {
        ArrayList<Integer> barcosFaltantes = new ArrayList<>();
        for (String tipo : tipos) {
            int cantidadFaltante = 0;
            for (Barco barco : barcos) {
                if (barco.getTipus().equals(tipo)) {
                    cantidadFaltante++;
                }
            }
            barcosFaltantes.add(cantidadFaltante);
        }
        return barcosFaltantes;
    }


}
