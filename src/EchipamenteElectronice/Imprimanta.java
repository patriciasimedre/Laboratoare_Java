package EchipamenteElectronice;

public class Imprimanta extends Echipament {
    private int ppm; // pagini pe minut
    private String rezolutie;
    private int paginiCartus;
    private ModTiparire modTiparire;

    public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                      int ppm, String rezolutie, int paginiCartus, ModTiparire modTiparire) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.paginiCartus = paginiCartus;
        this.modTiparire = modTiparire;
    }

    // Getter și Setter pentru modTiparire
    public ModTiparire getModTiparire() {
        return modTiparire;
    }

    public void setModTiparire(ModTiparire modTiparire) {
        this.modTiparire = modTiparire;
    }

    @Override
    public String toString() {
        return super.toString() + ", Imprimanta{" +
                "ppm=" + ppm +
                ", rezolutie='" + rezolutie + '\'' +
                ", paginiCartus=" + paginiCartus +
                ", modTiparire=" + modTiparire +
                '}';
    }
}
