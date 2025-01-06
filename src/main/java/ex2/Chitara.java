package ex2;

import java.util.Objects;

public class Chitara extends InstrumentMuzical {

    private TipChitara tipChitara;
    private int nrCorzi;

    public Chitara() {
        // constructor fără parametri pentru Jackson
    }

    public Chitara(String producator, double pret, TipChitara tipChitara, int nrCorzi) {
        super(producator, pret);
        this.tipChitara = tipChitara;
        this.nrCorzi = nrCorzi;
    }

    public TipChitara getTipChitara() {
        return tipChitara;
    }

    public void setTipChitara(TipChitara tipChitara) {
        this.tipChitara = tipChitara;
    }

    public int getNrCorzi() {
        return nrCorzi;
    }

    public void setNrCorzi(int nrCorzi) {
        this.nrCorzi = nrCorzi;
    }

    // Suprascriem equals și hashCode pentru a include și caracteristicile Chitarei
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chitara chitara)) return false;
        // Apelează super.equals() pentru campurile din InstrumentMuzical
        if (!super.equals(o)) return false;
        return nrCorzi == chitara.nrCorzi &&
                tipChitara == chitara.tipChitara;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipChitara, nrCorzi);
    }

    @Override
    public String toString() {
        return "Chitara{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                ", tipChitara=" + tipChitara +
                ", nrCorzi=" + nrCorzi +
                '}';
    }
}
