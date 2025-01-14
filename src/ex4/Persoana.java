package ex4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Persoana {
    private String nume;
    private String cnp;

    // Constructor
    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    // Gettere
    public String getNume() {
        return nume;
    }

    public String getCnp() {
        return cnp;
    }

    // Metoda pentru calcularea vârstei
    public int getVarsta() {
        // Extrage anul, luna și ziua nașterii din CNP
        int an = Integer.parseInt(cnp.substring(1, 3));
        int luna = Integer.parseInt(cnp.substring(3, 5));
        int zi = Integer.parseInt(cnp.substring(5, 7));

        // Determină secolul nașterii pe baza primei cifre din CNP
        char primaCifra = cnp.charAt(0);
        if (primaCifra == '1' || primaCifra == '2') {
            an += 1900;
        } else if (primaCifra == '5' || primaCifra == '6') {
            an += 2000;
        }

        // Data nașterii
        LocalDate dataNasterii = LocalDate.of(an, luna, zi);

        // Data curentă
        LocalDate dataCurenta = LocalDate.now();

        // Calcularea vârstei
        return (int) ChronoUnit.YEARS.between(dataNasterii, dataCurenta);
    }

    // Metoda pentru a afișa informațiile persoanei
    @Override
    public String toString() {
        return nume + ", CNP: " + cnp + ", Vârsta: " + getVarsta();
    }
}
