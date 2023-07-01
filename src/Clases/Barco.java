package Clases;

import java.util.ArrayList;

public class Barco {


    private int ID;
    private String nom;
    private String tipus;
    private float pes;
    private float eslora;
    private int capacitat;
    private int competi;
    private String estat;
    private int velocitat;
    private String centre;

    public Barco(int ID, String nom, String tipus, float pes, float eslora, int capacitat, int competi, String estat, int velocitat, String centre) {
        this.ID = ID;
        this.nom = nom;
        this.tipus = tipus;
        this.pes = pes;
        this.eslora = eslora;
        this.capacitat = capacitat;
        this.competi = competi;
        this.estat = estat;
        this.velocitat = velocitat;
        this.centre = centre;
    }



    public Barco getBarco(){
        return this;
    }

    public float getVelocitatReal(ArrayList<Sailor> sailors) {
        return this.velocitat * velocitatSailors(sailors);
    }

    public float velocitatSailors(ArrayList<Sailor> sailors){
        float velocity=0;

        for(Sailor sailor: sailors){
            velocity += sailor.getImpacteNavegant(getBarco());
        }
        return velocity;
    }
    public int getVelocitat() {
        return velocitat;
    }


    public String getNom() {
        return nom;
    }

    public String getTipus() {
        return tipus;
    }

    public float getEslora() {
        return eslora;
    }

    public int getCapacitat() {
        return capacitat;
    }


    public float getPes() {
        return pes;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }



    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }
}



