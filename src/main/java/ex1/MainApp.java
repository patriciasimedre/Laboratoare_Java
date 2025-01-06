package ex1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) {
        // Creăm un ObjectMapper pentru operații de citire/scriere JSON
        ObjectMapper mapper = new ObjectMapper();

        // Citim fișierul JSON și încărcăm datele într-un Map<Integer, Carte>
        Map<Integer, Carte> cartiMap = new HashMap<>();
        try (InputStream is = MainApp.class
                .getResourceAsStream("/carti.json")) {
            // Citim mai întâi un Map<String, Carte>
            Map<String, Carte> tempMap = mapper.readValue(is, new TypeReference<>() {});
            // Convertim cheile la Integer
            tempMap.forEach((k, v) -> cartiMap.put(Integer.valueOf(k), v));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CERINȚA 1
        // "Să se afișeze colecția (se vor afișa atât cheile cât şi valorile)"
        System.out.println("1) Afișăm colecția citită din JSON (cheie => Carte):");
        for (var entry : cartiMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // CERINȚA 2 (ȘTERGERE)
        // "Să se șteargă o carte din colecția Map"
        // Exemplu: ștergem cartea cu id=3 (Mitul lui Camus)
        System.out.println("\n2) Ștergem cartea cu id=3...");
        cartiMap.remove(3);
        System.out.println("După ștergere, colecția conține:");
        cartiMap.forEach((key, value) -> System.out.println(key + " => " + value));

        // CERINȚA 3 (ADAUGARE)
        // "Să se adauge o carte la colecția Map (se va utiliza metoda putIfAbsent)"
        // Exemplu: adăugăm o nouă carte cu id=7
        Carte carteNoua = new Carte("O nouă carte", "Un autor necunoscut", 2025);
        cartiMap.putIfAbsent(7, carteNoua);
        System.out.println("\n3) După adăugare cu putIfAbsent (id=7), colecția conține:");
        cartiMap.forEach((key, value) -> System.out.println(key + " => " + value));

        // CERINȚA 4 (SALVARE ÎN JSON)
        // "Să se salveze în fișierul JSON modificările făcute asupra colecției"
        // Rescriem fișierul carti.json cu noile date
        System.out.println("\n4) Salvăm modificările în fișierul JSON...");
        // Întrucât cheile sunt Integer, dar în JSON trebuie să fie String,
        // convertim invers Map<Integer, Carte> la Map<String, Carte>
        Map<String, Carte> mapDeSalvare = new LinkedHashMap<>();
        cartiMap.forEach((k, v) -> mapDeSalvare.put(String.valueOf(k), v));

        try {
            // Scriem direct în "target"
            File outputFile = new File("carti.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, mapDeSalvare);
            System.out.println("Modificările au fost salvate în " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CERINȚA 5
        // "Să se creeze o colecție Set<Carte> care extrage cărțile autorului Yuval Noah Harari"
        // Folosim Stream API şi colectori:
        System.out.println("\n5) Creăm un Set<Carte> cu toate cărțile autorului Yuval Noah Harari:");
        Set<Carte> setHarari = cartiMap.values().stream()
                .filter(c -> c.autorul().equalsIgnoreCase("Yuval Noah Harari"))
                .collect(Collectors.toSet());

        // Afișăm colecția creată cu ajutorul metodei forEach:
        setHarari.forEach(System.out::println);

        // CERINȚA 6
        // "Să se afișeze ordonat după titlul cărții elementele din colecția Set
        //  folosind Stream API, expresii lambda şi referințe la metode."
        System.out.println("\n6) Afișăm cărțile din set ordonate după titlu:");
        setHarari.stream()
                .sorted(Comparator.comparing(Carte::titlul))
                .forEach(System.out::println);

        // CERINȚA 7
        // "Să se afișeze datele celei mai vechi cărți din colecția Set
        //  folosind Stream API şi clasa Optional."
        System.out.println("\n7) Cea mai veche carte din set (după anul apariției):");
        Optional<Carte> ceaMaiVeche = setHarari.stream()
                .min(Comparator.comparingInt(Carte::anul));
        ceaMaiVeche.ifPresentOrElse(
                carte -> System.out.println("Cea mai veche carte este: " + carte),
                () -> System.out.println("Setul este gol, nu există cărți!")
        );
    }
}
