package ex3;

// Clasa Placa reprezintă o piesă de PAL care compune o piesă de mobilier
public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private boolean[] canturi;
    private int nr_bucati;

    // Constructor fără parametri (necesar pentru Jackson)
    public Placa() {
        // Obligatoriu pentru serializare/deserializare
    }

    // Constructor cu parametri
    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] canturi, int nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getLungime() {
        return lungime;
    }

    public void setLungime(int lungime) {
        this.lungime = lungime;
    }

    public int getLatime() {
        return latime;
    }

    public void setLatime(int latime) {
        this.latime = latime;
    }

    public Orientare getOrientare() {
        return orientare;
    }

    public void setOrientare(Orientare orientare) {
        this.orientare = orientare;
    }

    public boolean[] getCanturi() {
        return canturi;
    }

    public void setCanturi(boolean[] canturi) {
        this.canturi = canturi;
    }

    public int getNr_bucati() {
        return nr_bucati;
    }

    public void setNr_bucati(int nr_bucati) {
        this.nr_bucati = nr_bucati;
    }

    // Metodă toString pentru afișarea informațiilor
    @Override
    public String toString() {
        return "Placa: " + descriere
                + ", dim=" + lungime + "x" + latime
                + ", orientare=" + orientare
                + ", canturi=" + afiseazaCanturi()
                + ", nr_buc=" + nr_bucati;
    }

    // Metodă privată pentru a afișa vectorul de 4 booleeni (canturile)
    private String afiseazaCanturi() {
        if(canturi == null || canturi.length != 4) {
            return "nedefinit";
        }
        return "[" + canturi[0] + ", " + canturi[1] + ", " + canturi[2] + ", " + canturi[3] + "]";
    }
}
