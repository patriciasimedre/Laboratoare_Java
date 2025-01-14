package ex2;

import java.io.*;
import java.util.*;

public class ProcesareVers {
    private List<Vers> versuri;
    private String grupareLitere;
    private Random random;

    // Constructor
    public ProcesareVers(String grupareLitere) {
        this.versuri = new ArrayList<>();
        this.grupareLitere = grupareLitere;
        this.random = new Random();
    }

    // Metodă pentru citirea versurilor dintr-un fișier
    public void citesteDinFisier(String fisierIntrare) throws IOException {
        try (BufferedReader cititor = new BufferedReader(new FileReader(fisierIntrare))) {
            String linie;
            while ((linie = cititor.readLine()) != null) {
                versuri.add(new Vers(linie.trim()));
            }
        }
    }

    // Metodă pentru procesarea versurilor și scrierea lor într-un fișier de ieșire
    public void proceseazaSiScrieInFisier(String fisierIesire) throws IOException {
        try (BufferedWriter scriitor = new BufferedWriter(new FileWriter(fisierIesire))) {
            for (Vers vers : versuri) {
                int numarCuvinte = vers.numarCuvinte();
                int numarVocale = vers.numarVocale();
                boolean seIncheieCuGrupare = vers.seIncheieCu(grupareLitere);

                // Verificare pentru numărul aleator
                if (random.nextDouble() < 0.1) {
                    vers.transformaInMajuscule();
                }

                // Construirea liniei de ieșire
                String linieIesire = vers.getContinut() +
                        " [Cuvinte: " + numarCuvinte +
                        ", Vocale: " + numarVocale + "]";

                if (seIncheieCuGrupare) {
                    linieIesire += " *";
                }

                // Scrierea în fișier
                scriitor.write(linieIesire);
                scriitor.newLine();
            }
        }
    }
}
