/*
2. Fișierul cantec_in.txt conține versurile unui cântec la alegere. Să se scrie un
program care creează fișierul cantec_out.txt, care conține versurile cântecului original dar în
plus în dreptul fiecărui rând sunt afișate numărul de cuvinte de pe rând şi numărul de vocale
de pe fiecare rând. În dreptul rândurilor care se încheie cu o grupare de litere aleasă se va
pune o steluță (*). Rândurile pentru care un număr generat aleator este mai mic decât 0.1 vor
fi scrise cu majuscule (se vor genera aleator numere între 0 şi 1).

Se va defini o clasă Vers, care are o variabilă membră privată un șir de caractere
reprezentând versul și se va dezvolta câte un operator pentru fiecare cerință de mai sus (o
metodă care returnează numărul de cuvinte, o metodă care returnează numărul de vocale, etc).
Se va crea un vector de obiecte de tip Vers care va conține informația preluată din fișierul de
intrare.
 */

package ex2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fisierIntrare = "cantec_in.txt";
        String fisierIesire = "cantec_out.txt";
        String grupareLitere = "re"; // Gruparea de litere aleasă

        // Crearea procesorului de versuri
        ProcesareVers procesor = new ProcesareVers(grupareLitere);

        try {
            // Citirea versurilor din fișierul de intrare
            procesor.citesteDinFisier(fisierIntrare);

            // Procesarea și scrierea versurilor în fișierul de ieșire
            procesor.proceseazaSiScrieInFisier(fisierIesire);

            System.out.println("Fișierul de ieșire a fost generat cu succes.");
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}
