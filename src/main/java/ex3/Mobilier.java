package ex3;

import java.util.List;

// Clasa Mobilier reprezintă o piesă de mobilier (ex: birou, dulap) și conține o listă de Placa
public class Mobilier {
    private String nume;
    private List<Placa> placi;

    public Mobilier() {
        // Obligatoriu pentru serializare/deserializare
    }

    // Constructor cu parametri
    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Placa> getPlaci() {
        return placi;
    }

    public void setPlaci(List<Placa> placi) {
        this.placi = placi;
    }

    // Metodă toString pentru afișare
    @Override
    public String toString() {
        return "Mobilier: " + nume
                + ", numar_placi=" + (placi != null ? placi.size() : 0);
    }

    // Metodă care calculează aria totală a tuturor plăcilor (luând în considerare nr_bucati)
    public long calculeazaArieTotala() {
        if(placi == null) {
            return 0;
        }
        long suma = 0;
        for(Placa p : placi) {
            long aria = (long)p.getLungime() * p.getLatime() * p.getNr_bucati();
            suma += aria;
        }
        return suma;
    }
}
