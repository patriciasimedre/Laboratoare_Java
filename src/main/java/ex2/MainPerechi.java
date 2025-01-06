package ex2;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainPerechi {

    // Metodă statică pentru scrierea unei liste de PerecheNumere într-un fișier JSON
    public static void scriere(List<PerecheNumere> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Fisierul JSON de ieșire
            File file = new File("src/main/resources/perechi.json");
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă statică pentru citirea unei liste de PerecheNumere dintr-un fișier JSON
    public static List<PerecheNumere> citire() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/perechi.json");
            // Citim lista folosind TypeReference
            List<PerecheNumere> perechi = mapper.readValue(file, new TypeReference<List<PerecheNumere>>() {});
            return perechi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metoda main care demonstrează utilizarea claselor
    public static void main(String[] args) {
        // Construim o listă cu minim 3 perechi de numere
        List<PerecheNumere> lista = new ArrayList<>();
        lista.add(new PerecheNumere(3, 5));
        lista.add(new PerecheNumere(4, 6));
        lista.add(new PerecheNumere(5, 8));

        // Scriem lista în fișierul JSON
        scriere(lista);

        // Citim lista din fișier și o afișăm
        List<PerecheNumere> listaDinFisier = citire();
        if(listaDinFisier != null) {
            System.out.println("Lista citită din JSON:");
            for(PerecheNumere p : listaDinFisier) {
                System.out.println(p +
                        " | FibConsec=" + p.suntConsecutiveFibonacci() +
                        " | CMMC=" + p.cmmc() +
                        " | AceeasiSumCif=" + p.aceeasiSumaCifre() +
                        " | AceleasiCifPare=" + p.acelasiNumarCifrePare());
            }
        }
    }
}
