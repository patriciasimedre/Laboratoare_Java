/*
4. Să se realizeze un program care citește numele si CNP-ul pe care îl au n persoane.
Valoarea lui n se citește de la tastatură. Programul va afișa informațiile introduse și în plus
pentru fiecare persoana va afișa vârsta. Cât timp un CNP-ul este introdus greșit programul va
cere reintroducerea acestuia.

Se va crea clasa Persoana cu variabile membre private nume (String) şi cnp (String).
Clasa va avea constructor cu parametri, gettere si settere în funcție de necesități şi metoda
getVarsta() care va calcula şi va returna vârsta persoanei extrăgând data nașterii din CNP şi
citind din sistem data curentă. Se va utiliza clasa LocalDate. Se va crea un vector în care se
vor adăuga obiectele de tip Persoana. Fiecare element din vectorul va fi afișat pe un rând în
formatul nume, CNP, varsta.
 */

package ex4;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea numărului de persoane
        System.out.print("Introduceți numărul de persoane: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consumăm linia rămasă

        // Crearea vectorului de persoane
        Persoana[] persoane = new Persoana[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Introduceți datele pentru persoana " + (i + 1) + ":");

            // Citirea numelui
            System.out.print("Nume: ");
            String nume = scanner.nextLine();

            // Citirea CNP-ului cu validare
            String cnp;
            while (true) {
                System.out.print("CNP: ");
                cnp = scanner.nextLine();

                if (esteCnpValid(cnp)) {
                    break;
                } else {
                    System.out.println("CNP-ul introdus este invalid. Reîncercați.");
                }
            }

            // Adăugarea persoanei în vector
            persoane[i] = new Persoana(nume, cnp);
        }

        // Afișarea informațiilor despre fiecare persoană
        System.out.println("\nInformațiile introduse:");
        for (Persoana persoana : persoane) {
            System.out.println(persoana);
        }

        scanner.close();
    }

    // Metoda pentru validarea CNP-ului
    public static boolean esteCnpValid(String cnp) {
        if (cnp.length() != 13 || !Pattern.matches("\\d{13}", cnp)) {
            return false; // Verificăm lungimea și dacă toate caracterele sunt cifre
        }

        char primaCifra = cnp.charAt(0);
        if (primaCifra != '1' && primaCifra != '2' && primaCifra != '5' && primaCifra != '6') {
            return false; // Prima cifră trebuie să fie 1, 2, 5 sau 6
        }

        // Calculul cifrei de control
        int[] coeficienti = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        int suma = 0;
        for (int i = 0; i < 12; i++) {
            suma += Character.getNumericValue(cnp.charAt(i)) * coeficienti[i];
        }
        int cifraControl = suma % 11;
        if (cifraControl == 10) {
            cifraControl = 1;
        }

        // Verificăm cifra de control
        return Character.getNumericValue(cnp.charAt(12)) == cifraControl;
    }
}
