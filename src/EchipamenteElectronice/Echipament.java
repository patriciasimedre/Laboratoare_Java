package EchipamenteElectronice;

import java.io.Serializable;

public abstract class Echipament implements Serializable {
    private String denumire;
    private int nrInv;
    private double pret;
    private String zonaMag;
    private StareEchipament stare;

    public Echipament(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare) {
        this.denumire = denumire;
        this.nrInv = nrInv;
        this.pret = pret;
        this.zonaMag = zonaMag;
        this.stare = stare;
    }

    // Gettere și settere
    public String getDenumire() {
        return denumire;
    }

    public int getNrInv() {
        return nrInv;
    }

    public double getPret() {
        return pret;
    }

    public String getZonaMag() {
        return zonaMag;
    }

    public StareEchipament getStare() {
        return stare;
    }

    public void setStare(StareEchipament stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Echipament{" +
                "denumire='" + denumire + '\'' +
                ", nrInv=" + nrInv +
                ", pret=" + pret +
                ", zonaMag='" + zonaMag + '\'' +
                ", stare=" + stare +
                '}';
    }
}
