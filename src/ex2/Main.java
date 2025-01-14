/*

Să se scrie un program care citește din fișierul produse.csv informații referitoare la
produsele dintr-un magazin.
Pe fiecare linie se află: denumirea produsului, preţul (număr real), cantitatea (număr întreg),
data expirării (LocalDate). Cele patru elemente sunt separate prin caracterul "," (zahar, 1.5, 50, 2024-02-25).
Pentru fiecare rând citit se va crea un obiect de tip Produs care se va adăuga unei colecții de obiecte de tip List.
Se va defini o clasă Produs care va conține: variabile membre private corespunzătoare celor trei elemente care descriu un produs,
cel puţin un constructor, gettere si settere în funcție de necesități şi redefinirea metodei toString()
din clasa Object, metodă care va fi utilizată pentru afișarea produselor.
Să se realizeze un program care va dispune de un meniu și va implementa următoarele cerințe:

a) afișarea tuturor produselor din colecția List
b) afișarea produselor expirate
c) vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc.
Daca produsul ajunge la cantitate zero, se elimina din listă. În clasa Produs se va declara
o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs, luând în
considerare prețul produsului vândut şi cantitatea vândută .
d) afișarea produselor cu prețul minim (pot fi mai multe cu același preț)
e) salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură
într-un fișier de ieșire.

 */

package ex2;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<>();

        // Citirea produselor din fișierul produse.csv
        try (BufferedReader reader = new BufferedReader(new FileReader("produse.csv"))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] valori = linie.split(",");
                String denumire = valori[0].trim();
                double pret = Double.parseDouble(valori[1].trim());
                int cantitate = Integer.parseInt(valori[2].trim());
                LocalDate dataExpirarii = LocalDate.parse(valori[3].trim());

                produse.add(new Produs(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int optiune;

        // Meniu
        do {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișarea tuturor produselor");
            System.out.println("2. Afișarea produselor expirate");
            System.out.println("3. Vânzarea unui produs");
            System.out.println("4. Afișarea produselor cu prețul minim");
            System.out.println("5. Salvarea produselor cu cantitate mai mică decât o valoare în fișier");
            System.out.println("0. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumăm linia rămasă

            switch (optiune) {
                case 1:
                    // Afișarea tuturor produselor
                    produse.forEach(System.out::println);
                    break;

                case 2:
                    // Afișarea produselor expirate
                    LocalDate dataCurenta = LocalDate.now();
                    produse.stream()
                            .filter(p -> p.getDataExpirarii().isBefore(dataCurenta))
                            .forEach(System.out::println);
                    break;

                case 3:
                    // Vânzarea unui produs
                    System.out.print("Introduceți denumirea produsului: ");
                    String denumire = scanner.nextLine();
                    System.out.print("Introduceți cantitatea de vândut: ");
                    int cantitateDeVandut = scanner.nextInt();

                    Optional<Produs> produsOptional = produse.stream()
                            .filter(p -> p.getDenumire().equalsIgnoreCase(denumire))
                            .findFirst();

                    if (produsOptional.isPresent()) {
                        Produs produs = produsOptional.get();
                        if (produs.getCantitate() >= cantitateDeVandut) {
                            produs.setCantitate(produs.getCantitate() - cantitateDeVandut);
                            Produs.adaugaIncasari(produs.getPret() * cantitateDeVandut);
                            System.out.println("Produs vândut cu succes.");
                            if (produs.getCantitate() == 0) {
                                produse.remove(produs);
                                System.out.println("Produsul a fost eliminat din stoc.");
                            }
                        } else {
                            System.out.println("Cantitate insuficientă pe stoc.");
                        }
                    } else {
                        System.out.println("Produsul nu există.");
                    }
                    break;

                case 4:
                    // Afișarea produselor cu prețul minim
                    OptionalDouble pretMinimOptional = produse.stream()
                            .mapToDouble(Produs::getPret)
                            .min();

                    if (pretMinimOptional.isPresent()) {
                        double pretMinim = pretMinimOptional.getAsDouble();
                        produse.stream()
                                .filter(p -> p.getPret() == pretMinim)
                                .forEach(System.out::println);
                    }
                    break;

                case 5:
                    // Salvarea produselor cu cantitate mai mică decât o valoare
                    System.out.print("Introduceți valoarea cantității: ");
                    int valoareCantitate = scanner.nextInt();

                    List<Produs> produseFiltrate = produse.stream()
                            .filter(p -> p.getCantitate() < valoareCantitate)
                            .collect(Collectors.toList());

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("produse_filtrate.csv"))) {
                        for (Produs produs : produseFiltrate) {
                            writer.write(produs.getDenumire() + "," +
                                    produs.getPret() + "," +
                                    produs.getCantitate() + "," +
                                    produs.getDataExpirarii());
                            writer.newLine();
                        }
                        System.out.println("Produsele au fost salvate în produse_filtrate.csv.");
                    } catch (IOException e) {
                        System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
                    }
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
