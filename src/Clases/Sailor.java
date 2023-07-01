package Clases;


public class Sailor {


    private int ID;
    private String nom;
    private float pes;
    private int windsurf;
    private int optimist;
    private int laser;
    private int patiCatala;
    private int hobieDragon;
    private int hobieCat;
    private int ratioVictories;

    private boolean ubicat;


    public Sailor(int ID, String nom, float pes, int windsurf, int optimist, int laser, int patiCatala, int hobieDragon, int hobieCat, int ratioVictories) {
        this.ID = ID;
        this.nom = nom;
        this.pes = pes;

        this.windsurf = windsurf;
        this.optimist = optimist;
        this.laser = laser;
        this.patiCatala = patiCatala;
        this.hobieDragon = hobieDragon;
        this.hobieCat = hobieCat;
        this.ratioVictories = ratioVictories;
        this.ubicat=false;
    }



    public float getImpacteHabilitat(int habilitatTipusVaixell) {

        float habVaixell = (habilitatTipusVaixell % 10);
        float nRatio = (this.ratioVictories % 100);

        return (habVaixell / 10 + nRatio / 100) / 2;
    }

    public float getImpactePes(Barco barco) {
        return (100 - this.pes) / barco.getPes();

    }

    public float getImpacteNavegant(Barco barco) {
        int habilitat = tipoVaixell(barco.getTipus());
        return (getImpacteHabilitat(habilitat) + getImpactePes(barco)) / 2;
    }
    public int tipoVaixell(String nom){
        switch (nom) {
            case "Windsurf" -> {
                return getWindsurf();
            }
            case "Optimist" -> {
                return getOptimist();
            }
            case "Laser" -> {
                return getLaser();
            }
            case "Patí Català" -> {
                return getPatiCatala();
            }
            case "HobieDragoon" -> {
                return getHobieDragon();
            }
            case "HobieCat" -> {
                return getHobieCat();
            }
        }
        return 0;
    }
    public int getID() {
        return ID;
    }




    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public float getPes() {
        return pes;
    }

    public void setPes(float pes) {
        this.pes = pes;
    }


    public int getWindsurf() {
        return windsurf;
    }

    public void setWindsurf(int windsurf) {
        this.windsurf = windsurf;
    }

    public int getOptimist() {
        return optimist;
    }

    public void setOptimist(int optimist) {
        this.optimist = optimist;
    }

    public int getLaser() {
        return laser;
    }

    public void setLaser(int laser) {
        this.laser = laser;
    }

    public int getPatiCatala() {
        return patiCatala;
    }

    public void setPatiCatala(int patiCatala) {
        this.patiCatala = patiCatala;
    }

    public int getHobieDragon() {
        return hobieDragon;
    }

    public void setHobieDragon(int hobieDragon) {
        this.hobieDragon = hobieDragon;
    }

    public int getHobieCat() {
        return hobieCat;
    }

    public void setHobieCat(int hobieCat) {
        this.hobieCat = hobieCat;
    }

    public int getRatioVictories() {
        return ratioVictories;
    }

    public void setRatioVictories(int ratioVictories) {
        this.ratioVictories = ratioVictories;
    }


    public boolean isUbicat() {
        return ubicat;
    }

    public void setUbicat(boolean ubicat) {
        this.ubicat = ubicat;
    }
}


