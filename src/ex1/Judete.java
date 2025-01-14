/*
1. Fișierul judete_in.txt, conține lista neordonată a județelor din țară. Să se încarce
datele din fișier într-un tablou de String-uri și să se ordoneze acest tablou cu ajutorul metodei
sort() din clasa Arrays.
Să se returneze pe ce poziție se află în vectorul ordonat un județ
introdus de la tastatură. Se va utiliza metoda de căutare binară din clasa Arrays.
 */

package ex1;

import java.io.*;
import java.util.*;

public class Judete {
    public static void main(String[] args) {
        String fisierIntrare = "judete_in.txt";
        List<String> listaJudete = new ArrayList<>();

        // Citirea județelor din fișier
        try (BufferedReader cititor = new BufferedReader(new FileReader(fisierIntrare))) {
            String linie;
            while ((linie = cititor.readLine()) != null) {
                listaJudete.add(linie.trim());
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        // Convertirea listei într-un tablou de String-uri
        String[] judete = listaJudete.toArray(new String[0]);

        // Ordonarea tabloului de județe
        Arrays.sort(judete);

        // Afișarea județelor ordonate
        System.out.println("Județele ordonate sunt:");
        for (int i = 0; i < judete.length; i++) {
            System.out.println((i + 1) + ". " + judete[i]);
        }

        // Citirea unui județ de la tastatură
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți un județ pentru a căuta poziția acestuia: ");
        String judetCautat = scanner.nextLine().trim();

        // Căutarea binară a județului
        int pozitie = Arrays.binarySearch(judete, judetCautat);

        // Afișarea poziției sau un mesaj corespunzător
        if (pozitie >= 0) {
            System.out.println("Județul " + judetCautat + " se află pe poziția " + (pozitie + 1) + " în vectorul ordonat.");
        } else {
            System.out.println("Județul " + judetCautat + " nu a fost găsit în lista județelor.");
        }

        scanner.close();
    }
}
