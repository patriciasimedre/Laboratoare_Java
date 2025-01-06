package ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {
        // 1) Creăm colecția polimorfă de instrumente
        Set<InstrumentMuzical> instrumente = new HashSet<>();

        // Adăugăm 3 chitări
        instrumente.add(new Chitara("Fender", 2500, TipChitara.ELECTRICA, 6));
        instrumente.add(new Chitara("Gibson", 3200, TipChitara.ELECTRICA, 8));
        instrumente.add(new Chitara("Yamaha", 1800, TipChitara.ACUSTICA, 12));

        // Adăugăm 3 seturi de tobe
        instrumente.add(new SetTobe("Pearl", 3500, TipTobe.ACUSTICE, 5, 2));
        instrumente.add(new SetTobe("Roland", 4800, TipTobe.ELECTRONICE, 4, 1));
        instrumente.add(new SetTobe("Yamaha", 2900, TipTobe.ACUSTICE, 6, 3));

        // 2) Configurăm ObjectMapper cu BasicPolymorphicTypeValidator
        ObjectMapper mapper = new ObjectMapper();

        // Permitem subtipurile din java.util (pentru HashSet, LinkedHashSet etc.),
        // plus subtipurile de InstrumentMuzical (chitări, tobe),
        // și enum-urile (TipTobe, TipChitara).
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.")           // pentru HashSet etc.
                .allowIfSubType("ex2.TipTobe")          // pentru enum-ul tipTobe
                .allowIfSubType("ex2.TipChitara")       // pentru enum-ul tipChitara
                .allowIfBaseType(Enum.class)            // sau direct pt. toate enum-urile
                .allowIfBaseType(InstrumentMuzical.class)
                .build();

        // Activează typing pe TOT (EVERYTHING) – inclusiv colecții, enum, etc.
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.EVERYTHING);

        // 3) Scriem colecția în fișierul instrumente.json
        File outFile = new File("instrumente.json");
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(outFile, instrumente);
            System.out.println("Colecția a fost salvată în " + outFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4) Citim colecția din instrumente.json
        Set<InstrumentMuzical> instrumenteCitite = null;
        try {
            instrumenteCitite = mapper.readValue(outFile, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Eroare la citirea fișierului JSON.");
            return;
        }

        // 5) Afișăm tipul concret al set-ului (HashSet, LinkedHashSet etc.)
        System.out.println("\nTipul concret al colecției după deserializare: " +
                instrumenteCitite.getClass().getName());

        // 6) Verificăm dacă set-ul permite duplicate
        // Adăugăm un obiect identic cu primul (chitară Fender).
        Chitara chDup = new Chitara("Fender", 2500, TipChitara.ELECTRICA, 6);

        System.out.println("\nDimensiune set INAINTE de adăugare duplicat: " + instrumenteCitite.size());
        boolean added = instrumenteCitite.add(chDup);
        if (!added) {
            System.out.println("Nu s-a adăugat obiectul (Set NU permite duplicate).");
        } else {
            System.out.println("Obiectul a fost adăugat (Set permite duplicate?).");
        }
        System.out.println("Dimensiune set DUPĂ adăugare duplicat: " + instrumenteCitite.size());

        System.out.println("\nObiecte din set:");
        instrumenteCitite.forEach(System.out::println);

        // 7) Ștergem instrumentele mai scumpe de 3000 RON
        System.out.println("\nȘtergem instrumentele care depășesc 3000 RON");
        instrumenteCitite.removeIf(instr -> instr.getPret() > 3000);
        System.out.println("După ștergere:");
        instrumenteCitite.forEach(System.out::println);

        // 8) Afișăm toate chitarele, folosind instanceof
        System.out.println("\nToate chitarele (instanceof Chitara):");
        instrumenteCitite.stream()
                .filter(instr -> instr instanceof Chitara)
                .forEach(System.out::println);

        // 9) Afișăm toate tobele, folosind getClass()
        System.out.println("\nToate tobele (getClass() == SetTobe.class):");
        instrumenteCitite.stream()
                .filter(instr -> instr.getClass() == SetTobe.class)
                .forEach(System.out::println);

        // 10) Găsim chitara cu cele mai multe corzi
        System.out.println("\nChitara cu cele mai multe corzi:");
        Optional<Chitara> chitaraMaxCorzi = instrumenteCitite.stream()
                .filter(instr -> instr instanceof Chitara)
                .map(instr -> (Chitara) instr)
                .max(Comparator.comparingInt(Chitara::getNrCorzi));

        chitaraMaxCorzi.ifPresentOrElse(
                c -> System.out.println("Chitara cu cele mai multe corzi: " + c),
                () -> System.out.println("Nu există chitare în set!")
        );

        // 11) Afișăm tobele acustice, ordonate după nrTobe
        System.out.println("\nTobele acustice, ordonate după nrTobe:");
        instrumenteCitite.stream()
                .filter(instr -> instr instanceof SetTobe)
                .map(instr -> (SetTobe) instr)
                .filter(t -> t.getTipTobe() == TipTobe.ACUSTICE)
                .sorted(Comparator.comparingInt(SetTobe::getNrTobe))
                .forEach(System.out::println);
    }
}