package EchipamenteElectronice;

public class SistemCalcul extends Echipament {
    private String tipMonitor;
    private double vitezaProcesor;
    private int capacitateHdd;
    private SistemOperare sistemOperare;

    public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                        String tipMonitor, double vitezaProcesor, int capacitateHdd, SistemOperare sistemOperare) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.tipMonitor = tipMonitor;
        this.vitezaProcesor = vitezaProcesor;
        this.capacitateHdd = capacitateHdd;
        this.sistemOperare = sistemOperare;
    }

    // Getter și Setter pentru sistemOperare
    public SistemOperare getSistemOperare() {
        return sistemOperare;
    }

    public void setSistemOperare(SistemOperare sistemOperare) {
        this.sistemOperare = sistemOperare;
    }

    @Override
    public String toString() {
        return super.toString() + ", SistemCalcul{" +
                "tipMonitor='" + tipMonitor + '\'' +
                ", vitezaProcesor=" + vitezaProcesor +
                ", capacitateHdd=" + capacitateHdd +
                ", sistemOperare=" + sistemOperare +
                '}';
    }
}

