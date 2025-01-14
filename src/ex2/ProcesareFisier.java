/*
2. Să se scrie un program care citește un set de numerele din fișierul de intrare in.txt, care
conține câte un număr pe un rând, având valorile din figura 18. Programul va determină suma
lor, media aritmetică, valoarea minimă, valoarea maximă, va afișa aceste valori pe ecran și le
va scrie în fișierul de ieșire out.txt. Media aritmetică va fi afișată ca un număr real.
 */

package ex2;

import java.io.*;
import java.util.*;


public class ProcesareFisier {
    public static void main(String[] args) {
        String fisierIntrare = "in.txt";
        String fisierIesire = "out.txt";

        List<Integer> numere = new ArrayList<>();

        // Citirea numerelor din fișierul de intrare
        try (BufferedReader cititor = new BufferedReader(new FileReader(fisierIntrare))) {
            String linie;
            while ((linie = cititor.readLine()) != null) {
                numere.add(Integer.parseInt(linie.trim()));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        // Calcularea valorilor cerute
        int suma = numere.stream().mapToInt(Integer::intValue).sum();
        double medie = numere.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        int minim = numere.stream().mapToInt(Integer::intValue).min().orElse(0);
        int maxim = numere.stream().mapToInt(Integer::intValue).max().orElse(0);

        // Afișarea valorilor la consolă
        System.out.println("Suma: " + suma);
        System.out.println("Media: " + medie);
        System.out.println("Minim: " + minim);
        System.out.println("Maxim: " + maxim);

        // Scrierea valorilor în fișierul de ieșire
        try (BufferedWriter scriitor = new BufferedWriter(new FileWriter(fisierIesire))) {
            scriitor.write("Suma: " + suma + "\n");
            scriitor.write("Media: " + medie + "\n");
            scriitor.write("Minim: " + minim + "\n");
            scriitor.write("Maxim: " + maxim + "\n");
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}
