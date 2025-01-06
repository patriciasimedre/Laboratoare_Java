package ex2;

import java.util.Objects;

public class SetTobe extends InstrumentMuzical {

    private TipTobe tipTobe;
    private int nrTobe;
    private int nrCinele;

    public SetTobe() {
        // Constructor necesar pentru Jackson
    }

    public SetTobe(String producator, double pret, TipTobe tipTobe, int nrTobe, int nrCinele) {
        super(producator, pret);
        this.tipTobe = tipTobe;
        this.nrTobe = nrTobe;
        this.nrCinele = nrCinele;
    }

    public TipTobe getTipTobe() {
        return tipTobe;
    }

    public void setTipTobe(TipTobe tipTobe) {
        this.tipTobe = tipTobe;
    }

    public int getNrTobe() {
        return nrTobe;
    }

    public void setNrTobe(int nrTobe) {
        this.nrTobe = nrTobe;
    }

    public int getNrCinele() {
        return nrCinele;
    }

    public void setNrCinele(int nrCinele) {
        this.nrCinele = nrCinele;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetTobe setTobe)) return false;
        // Apelează super.equals() pentru campurile din InstrumentMuzical
        if (!super.equals(o)) return false;
        return nrTobe == setTobe.nrTobe &&
                nrCinele == setTobe.nrCinele &&
                tipTobe == setTobe.tipTobe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipTobe, nrTobe, nrCinele);
    }

    @Override
    public String toString() {
        return "SetTobe{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                ", tipTobe=" + tipTobe +
                ", nrTobe=" + nrTobe +
                ", nrCinele=" + nrCinele +
                '}';
    }
}