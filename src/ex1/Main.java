package ex1;

import java.io.*;
import java.util.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String fisierIntrare = "in.txt";
        List<Parabola> parabole = new ArrayList<>();

        // Citirea parabolelor din fișier
        try (BufferedReader reader = new BufferedReader(new FileReader(fisierIntrare))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] coeficienti = linie.trim().split("\\s+");
                int a = Integer.parseInt(coeficienti[0]);
                int b = Integer.parseInt(coeficienti[1]);
                int c = Integer.parseInt(coeficienti[2]);
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        // Setăm Locale pentru afișare consistentă
        Locale.setDefault(Locale.US);

        // Afișarea parabolelor și a vârfurilor
        System.out.println("Parabolele și vârfurile lor:");
        for (Parabola parabola : parabole) {
            double[] varf = parabola.calculeazaVarf();
            System.out.printf("%s, Vârf: (%.2f, %.2f)%n", parabola, varf[0], varf[1]);
        }

        // Calcularea și afișarea mijlocului și lungimii segmentului dintre două parabole
        if (parabole.size() >= 2) {
            Parabola parabola1 = parabole.get(0);
            Parabola parabola2 = parabole.get(1);

            // Mijlocul segmentului
            double[] mijloc = Parabola.mijlocSegment(parabola1, parabola2);
            System.out.printf("Mijlocul segmentului dintre parabola 1 și parabola 2: (%.2f, %.2f)%n",
                    mijloc[0], mijloc[1]);

            // Lungimea segmentului
            double lungime = Parabola.lungimeSegment(parabola1, parabola2);
            System.out.printf("Lungimea segmentului dintre parabola 1 și parabola 2: %.2f%n", lungime);
        }
    }
}
