package ex2;

import java.util.Random;

public class Vers {
    private String continut;

    // Constructor
    public Vers(String continut) {
        this.continut = continut;
    }

    // Metodă pentru numărul de cuvinte
    public int numarCuvinte() {
        if (continut.trim().isEmpty()) return 0; // Verificare pentru linii goale
        return continut.split("\\s+").length;
    }

    // Metodă pentru numărul de vocale
    public int numarVocale() {
        int count = 0;
        for (char c : continut.toLowerCase().toCharArray()) {
            if ("aeiouăîâ".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // Metodă pentru verificarea grupării de litere la sfârșit
    public boolean seIncheieCu(String grupare) {
        return continut.endsWith(grupare);
    }

    // Metodă pentru a transforma în majuscule
    public void transformaInMajuscule() {
        continut = continut.toUpperCase();
    }

    // Metodă pentru a returna conținutul versului
    public String getContinut() {
        return continut;
    }
}
