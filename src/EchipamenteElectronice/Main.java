package EchipamenteElectronice;

import java.io.*;
import java.util.*;

public class Main {

    // Metodă statică pentru serializarea colecției într-un fișier binar
    public static void serialize(List<Echipament> echipamente, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(echipamente);
            System.out.println("Colecția a fost serializată în fișierul " + fileName);
        } catch (IOException e) {
            System.out.println("Eroare la serializare: " + e.getMessage());
        }
    }

    // Metodă statică pentru deserializarea colecției dintr-un fișier binar
    public static List<Echipament> deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Echipament> echipamente = (List<Echipament>) ois.readObject();
            System.out.println("Colecția a fost deserializată din fișierul " + fileName);
            return echipamente;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la deserializare: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Echipament> echipamente = new ArrayList<>();

        // Citirea din fișierul text
        try (BufferedReader reader = new BufferedReader(new FileReader("echipamente.txt"))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] valori = linie.split(";");
                String denumire = valori[0];
                int nrInv = Integer.parseInt(valori[1]);
                double pret = Double.parseDouble(valori[2]);
                String zonaMag = valori[3];
                StareEchipament stare = StareEchipament.valueOf(valori[4].toUpperCase());

                switch (valori[5].toLowerCase()) {
                    case "imprimanta":
                        echipamente.add(new Imprimanta(
                                denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(valori[6]),
                                valori[7],
                                Integer.parseInt(valori[8]),
                                ModTiparire.valueOf(valori[9].toUpperCase())
                        ));
                        break;
                    case "copiator":
                        echipamente.add(new Copiator(
                                denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(valori[6]),
                                FormatCopiere.valueOf(valori[7].toUpperCase())
                        ));
                        break;
                    case "sistem de calcul":
                        echipamente.add(new SistemCalcul(
                                denumire, nrInv, pret, zonaMag, stare,
                                valori[6],
                                Double.parseDouble(valori[7]),
                                Integer.parseInt(valori[8]),
                                SistemOperare.valueOf(valori[9].toUpperCase())
                        ));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int optiune;

        // Meniu principal
        do {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișarea tuturor echipamentelor");
            System.out.println("2. Afișarea imprimantelor");
            System.out.println("3. Afișarea copiatoarelor");
            System.out.println("4. Afișarea sistemelor de calcul");
            System.out.println("5. Modificarea stării unui echipament");
            System.out.println("6. Setarea modului de tipărire al unei imprimante");
            System.out.println("7. Setarea formatului de copiere pentru un copiator");
            System.out.println("8. Instalarea unui sistem de operare pe un sistem de calcul");
            System.out.println("9. Afișarea echipamentelor vândute");
            System.out.println("10. Serializarea colecției");
            System.out.println("11. Deserializarea colecției");
            System.out.println("0. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumăm linia rămasă

            switch (optiune) {
                case 1:
                    // Afișarea tuturor echipamentelor
                    echipamente.forEach(System.out::println);
                    break;

                case 2:
                    // Afișarea imprimantelor
                    echipamente.stream()
                            .filter(e -> e instanceof Imprimanta)
                            .forEach(System.out::println);
                    break;

                case 3:
                    // Afișarea copiatoarelor
                    echipamente.stream()
                            .filter(e -> e instanceof Copiator)
                            .forEach(System.out::println);
                    break;

                case 4:
                    // Afișarea sistemelor de calcul
                    echipamente.stream()
                            .filter(e -> e instanceof SistemCalcul)
                            .forEach(System.out::println);
                    break;

                case 5:
                    // Modificarea stării unui echipament
                    System.out.print("Introduceți numărul de inventar: ");
                    int nrInv = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduceți noua stare (ACHIZITIONAT, EXPUS, VANDUT): ");
                    String stareNoua = scanner.nextLine().toUpperCase();

                    echipamente.stream()
                            .filter(e -> e.getNrInv() == nrInv)
                            .findFirst()
                            .ifPresent(e -> e.setStare(StareEchipament.valueOf(stareNoua)));
                    break;

                case 6:
                    // Setarea modului de tipărire pentru imprimante
                    System.out.print("Introduceți numărul de inventar: ");
                    nrInv = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduceți modul de tipărire (COLOR, ALB_NEGRU): ");
                    String modTiparire = scanner.nextLine().toUpperCase();

                    echipamente.stream()
                            .filter(e -> e instanceof Imprimanta && e.getNrInv() == nrInv)
                            .map(e -> (Imprimanta) e)
                            .findFirst()
                            .ifPresent(imprimanta -> imprimanta.setModTiparire(ModTiparire.valueOf(modTiparire)));
                    break;

                case 7:
                    // Setarea formatului de copiere pentru copiatoare
                    System.out.print("Introduceți numărul de inventar: ");
                    nrInv = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduceți formatul de copiere (A3, A4): ");
                    String formatCopiere = scanner.nextLine().toUpperCase();

                    echipamente.stream()
                            .filter(e -> e instanceof Copiator && e.getNrInv() == nrInv)
                            .map(e -> (Copiator) e)
                            .findFirst()
                            .ifPresent(copiator -> copiator.setFormatCopiere(FormatCopiere.valueOf(formatCopiere)));
                    break;

                case 8:
                    // Instalarea unui sistem de operare pe un sistem de calcul
                    System.out.print("Introduceți numărul de inventar: ");
                    nrInv = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduceți sistemul de operare (WINDOWS, LINUX): ");
                    String sistemOperare = scanner.nextLine().toUpperCase();

                    echipamente.stream()
                            .filter(e -> e instanceof SistemCalcul && e.getNrInv() == nrInv)
                            .map(e -> (SistemCalcul) e)
                            .findFirst()
                            .ifPresent(sistem -> sistem.setSistemOperare(SistemOperare.valueOf(sistemOperare)));
                    break;

                case 9:
                    // Afișarea echipamentelor vândute
                    echipamente.stream()
                            .filter(e -> e.getStare() == StareEchipament.VANDUT)
                            .forEach(System.out::println);
                    break;

                case 10:
                    // Serializarea colecției
                    serialize(echipamente, "echip.bin");
                    break;

                case 11:
                    // Deserializarea colecției
                    echipamente = deserialize("echip.bin");
                    break;

                case 0:
                    System.out.println("La revedere!");
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
            }
        } while (optiune != 0);

        scanner.close();
    }
}
