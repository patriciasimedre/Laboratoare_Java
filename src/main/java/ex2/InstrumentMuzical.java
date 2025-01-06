package ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;

// Adnotarea necesară pentru serializare polimorfă
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical {
    protected String producator;
    protected double pret;

    public InstrumentMuzical() {
    }

    public InstrumentMuzical(String producator, double pret) {
        this.producator = producator;
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    // Metodă comună pentru a ajuta la equality, însă se va extinde/înlocui în clasele derivate
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstrumentMuzical that)) return false;
        return Double.compare(that.pret, pret) == 0 &&
                Objects.equals(producator, that.producator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producator, pret);
    }

    @Override
    public String toString() {
        return "InstrumentMuzical{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }
}
