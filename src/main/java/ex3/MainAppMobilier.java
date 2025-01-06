package ex3;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Clasa care rezolvă cerințele:
// a) Citește din mobilier.json într-o List<Mobilier> și afișează
// b) Afișează elementele de mobilier și plăcile
// c) Afișează plăcile pentru un nume de piesă anume
// d) Afișează numărul colilor de pal (2800 x 2070) necesare, bazat pe arie

public class MainAppMobilier {

    public static void main(String[] args) {
        // Citim lista de mobilier din fișierul mobilier.json
        List<Mobilier> listaMobilier = citireMobilierDinJson("src/main/resources/mobilier.json");

        // a) Afișăm direct lista (fie doar piesele, fie mai detaliat)
        System.out.println("Lista de piese de mobilier din fisier:");
        if(listaMobilier != null) {
            for(Mobilier m : listaMobilier) {
                System.out.println(m);
            }
        }

        // b) Afișăm elementele de mobilier și plăcile lor
        System.out.println("\nAfisare completa (mobilier si placile componente):");
        afiseazaMobilierSiPlaci(listaMobilier);

        // c) Solicităm utilizatorului numele unei piese și afișăm plăcile ei
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntrodu numele piesei de mobilier pentru detalii (ex: mobilier corp 1): ");
        String numeCautat = sc.nextLine();
        System.out.println("\nPlaci pentru mobilierul: " + numeCautat);
        afiseazaPlaciPentruMobilier(listaMobilier, numeCautat);

        // d) Calculăm numărul de coli de PAL necesare, pe baza ariei
        // (dimensiunea colii: 2800 x 2070 mm)
        long nrColi = calculeazaNrColiPal(listaMobilier, numeCautat);
        System.out.println("\nNumarul estimativ de coli de PAL pentru " + numeCautat + ": " + nrColi);

        sc.close();
    }

    // Metodă pentru citirea listei de mobilier din fișierul JSON
    public static List<Mobilier> citireMobilierDinJson(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(path);
            List<Mobilier> mob = mapper.readValue(file, new TypeReference<List<Mobilier>>() {});
            return mob;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // b) Afișează fiecare piesă de mobilier și plăcile care o compun
    public static void afiseazaMobilierSiPlaci(List<Mobilier> lista) {
        if(lista == null) {
            return;
        }
        for(Mobilier m : lista) {
            System.out.println(m);
            if(m.getPlaci() != null) {
                for(Placa placa : m.getPlaci()) {
                    System.out.println("   -> " + placa);
                }
            }
        }
    }

    // c) Afișează plăcile pentru un anumit mobilier (dacă există)
    public static void afiseazaPlaciPentruMobilier(List<Mobilier> lista, String numeMobilier) {
        if(lista == null) {
            return;
        }
        boolean gasit = false;
        for(Mobilier m : lista) {
            if(m.getNume().equalsIgnoreCase(numeMobilier)) {
                gasit = true;
                if(m.getPlaci() != null) {
                    for(Placa p : m.getPlaci()) {
                        System.out.println("   -> " + p);
                    }
                } else {
                    System.out.println("   (nu are placi definite)");
                }
            }
        }
        if(!gasit) {
            System.out.println("Nu exista mobilier cu numele " + numeMobilier);
        }
    }

    // d) Afișează estimativ numărul de coli de PAL (fără să ținem cont de tăieturi)
    // coala are 2800 x 2070 mm => 5.796.000 mm^2
    public static long calculeazaNrColiPal(List<Mobilier> lista, String numeMobilier) {
        if(lista == null) {
            return 0;
        }
        long ariaColie = 2800L * 2070L; // mm^2 = 5.796.000
        for(Mobilier m : lista) {
            if(m.getNume().equalsIgnoreCase(numeMobilier)) {
                long arieTot = m.calculeazaArieTotala();
                // Împărțim la aria unei coli și rotunjim în sus
                long nrColi = (long) Math.ceil((double)arieTot / ariaColie);
                return nrColi;
            }
        }
        return 0;
    }
}
