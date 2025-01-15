package EchipamenteElectronice;

public class Copiator extends Echipament {
    private int paginiToner;
    private FormatCopiere formatCopiere;

    public Copiator(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                    int paginiToner, FormatCopiere formatCopiere) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.paginiToner = paginiToner;
        this.formatCopiere = formatCopiere;
    }

    // Getter și Setter pentru formatCopiere
    public FormatCopiere getFormatCopiere() {
        return formatCopiere;
    }

    public void setFormatCopiere(FormatCopiere formatCopiere) {
        this.formatCopiere = formatCopiere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Copiator{" +
                "paginiToner=" + paginiToner +
                ", formatCopiere=" + formatCopiere +
                '}';
    }
}
