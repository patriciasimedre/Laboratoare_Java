package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Citim lista de angajați din JSON
        var angajati = citesteAngajatiDinJson("angajati.json");

        // 2. Rezolvăm cerințele:

        // Cerința 1: Afișarea listei de angajați folosind referințe la metode (method reference)
        System.out.println("=== Cerința 1: Listă de angajați ===");
        angajati.forEach(System.out::println);  // referință la metoda println

        // Cerința 2: Afișarea angajaților care au salariul peste 2500 RON
        // Folosim stream(), filtrare cu un Predicate lambda
        System.out.println("\n=== Cerința 2: Angajați cu salariul > 2500 ===");
        angajati.stream()
                .filter(a -> a.getSalariu() > 2500)
                .forEach(System.out::println);

        // Cerința 3: Crearea unei liste cu angajații din luna aprilie a anului trecut
        //            care au funcție de conducere (postul conține 'sef' sau 'director')
        // Anul curent
        var anulCurent = LocalDate.now().getYear();
        var anulTrecut = anulCurent - 1;
        System.out.println("\n=== Cerința 3: Angajații din aprilie anul trecut, funcție conducere ===");
        var listaAprilieAnTrecut = angajati.stream()
                .filter(a -> a.getDataAngajarii().getYear() == anulTrecut)
                .filter(a -> a.getDataAngajarii().getMonthValue() == 4)
                .filter(a -> {
                    // Verificăm dacă postul conține "sef" sau "director"
                    var post = a.getPostul().toLowerCase();
                    return post.contains("sef") || post.contains("director");
                })
                .collect(Collectors.toList());
        listaAprilieAnTrecut.forEach(System.out::println);

        // Cerința 4: Afișarea angajaților care NU au funcție de conducere
        // (nu conțin 'director' sau 'sef'), în ordine descrescătoare a salariului
        System.out.println("\n=== Cerința 4: Angajați fără funcție de conducere, ordonați descrescător după salariu ===");
        angajati.stream()
                .filter(a -> {
                    var post = a.getPostul().toLowerCase();
                    return !post.contains("director") && !post.contains("sef");
                })
                // sortare descrescătoare după salariu
                .sorted((a1, a2) -> Float.compare(a2.getSalariu(), a1.getSalariu()))
                .forEach(System.out::println);

        // Cerința 5: Listă de String-uri cu numele angajaților scrise cu majuscule
        //            (folosim map() și colectăm într-o listă)
        System.out.println("\n=== Cerința 5: Numele angajaților cu majuscule ===");
        var numeCuMajuscule = angajati.stream()
                .map(a -> a.getNumele().toUpperCase())
                .collect(Collectors.toList());
        numeCuMajuscule.forEach(System.out::println);

        // Cerința 6: Afișarea salariilor mai mici de 3000 RON (doar salariile)
        System.out.println("\n=== Cerința 6: Salarii < 3000 ===");
        angajati.stream()
                .map(Angajat::getSalariu)   // referință la metodă
                .filter(s -> s < 3000)
                .forEach(System.out::println);

        // Cerința 7: Afișarea datelor primului angajat al firmei
        // Folosim min() cu un comparator pe dataAngajarii
        // Rezultatul este un Optional<Angajat>
        System.out.println("\n=== Cerința 7: Primul angajat al firmei ===");
        Optional<Angajat> primulAngajat = angajati.stream()
                .min(Comparator.comparing(Angajat::getDataAngajarii));
        if (primulAngajat.isPresent()) {
            System.out.println("Primul angajat este: " + primulAngajat.get());
        } else {
            System.out.println("Nu există angajați în firmă.");
        }

        // Cerința 8: Statistici referitoare la salariul angajaților (mediu, min, max)
        System.out.println("\n=== Cerința 8: Statistici salarii ===");
        DoubleSummaryStatistics stats = angajati.stream()
                .collect(Collectors.summarizingDouble(Angajat::getSalariu));
        System.out.println("Salariul mediu: " + stats.getAverage());
        System.out.println("Salariul minim: " + stats.getMin());
        System.out.println("Salariul maxim: " + stats.getMax());

        // Cerința 9: Afișarea unui mesaj care indică dacă există cel puțin un “Ion”
        // Folosim findAny() care returnează un Optional și ifPresentOrElse
        System.out.println("\n=== Cerința 9: Verificare dacă există un angajat cu numele 'Ion' ===");
        angajati.stream()
                .filter(a -> a.getNumele().contains("Ion")) // sau equals, depinde ce vrei
                .findAny()
                .ifPresentOrElse(
                        (a) -> System.out.println("Firma are cel puțin un Ion angajat: " + a),
                        () -> System.out.println("Firma nu are niciun Ion angajat")
                );

        // Cerința 10: Afișarea numărului de persoane care s-au angajat în vara anului precedent
        // Vara = iunie (6), iulie (7), august (8), anulTrecut
        System.out.println("\n=== Cerința 10: Numărul de persoane angajate în vara anului precedent ===");
        long nrVaraAnTrecut = angajati.stream()
                .filter(a -> a.getDataAngajarii().getYear() == anulTrecut)
                .filter(a -> {
                    int luna = a.getDataAngajarii().getMonthValue();
                    return luna >= 6 && luna <= 8; // iunie, iulie, august
                })
                .count();
        System.out.println("Număr persoane angajate vara anului trecut: " + nrVaraAnTrecut);

        // Exemplu de scriere a listei de angajați înapoi în JSON (dacă vrei să salvezi
        // eventualele modificări):
        // scrieAngajatiInJson("angajati_output.json", angajati);
    }

    /**
     * Metodă pentru citirea unei liste de Angajat dintr-un fișier JSON.
     */
    public static List<Angajat> citesteAngajatiDinJson(String fileName) {
        // Folosim var (inferență de tip din Java 10+)
        var mapper = new ObjectMapper();
        // Înregistrăm modulul pentru date/timp (LocalDate)
        mapper.registerModule(new JavaTimeModule());
        // Pentru a scrie datele sub formă de [an, luna, zi], dezactivăm Timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try (InputStream is = Files.newInputStream(Path.of("src", "main", "resources", fileName))) {
            return mapper.readValue(is, new TypeReference<List<Angajat>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // returnăm o listă goală în caz de eroare
        }
    }

    /**
     * Metodă pentru scrierea unei liste de Angajat într-un fișier JSON.
     */
    public static void scrieAngajatiInJson(String fileName, List<Angajat> angajati) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try (OutputStream os = Files.newOutputStream(Path.of("src", "main", "resources", fileName))) {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(os, angajati);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
