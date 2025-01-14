/*
3. Să se insereze într-o anumită poziție a unui șir de caractere, un alt șir. Datele vor fi
preluate de la tastatură sau din fișier. Să se șteargă o porțiune a unui șir de caractere care
începe dintr-o anumită poziție și are un anumit număr de caractere. Se recomandă utilizarea
clasei StringBuilder.
 */

package ex3;

import java.util.Scanner;

public class ManipulareSiruri {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea șirului principal de caractere
        System.out.print("Introduceți șirul principal: ");
        String sirPrincipal = scanner.nextLine();

        // Citirea șirului de inserat
        System.out.print("Introduceți șirul care va fi inserat: ");
        String sirDeInserat = scanner.nextLine();

        // Citirea poziției de inserare
        System.out.print("Introduceți poziția unde doriți să inserați șirul: ");
        int pozitieInserare = scanner.nextInt();

        // Utilizarea StringBuilder pentru inserare
        StringBuilder stringBuilder = new StringBuilder(sirPrincipal);
        if (pozitieInserare >= 0 && pozitieInserare <= stringBuilder.length()) {
            stringBuilder.insert(pozitieInserare, sirDeInserat);
            System.out.println("Șirul după inserare: " + stringBuilder.toString());
        } else {
            System.out.println("Poziția de inserare este invalidă!");
        }

        // Citirea poziției de ștergere și a numărului de caractere de șters
        System.out.print("Introduceți poziția de început pentru ștergere: ");
        int pozitieStergere = scanner.nextInt();

        System.out.print("Introduceți numărul de caractere de șters: ");
        int numarCaractereStergere = scanner.nextInt();

        // Ștergerea porțiunii specificate
        if (pozitieStergere >= 0 && pozitieStergere < stringBuilder.length() &&
                pozitieStergere + numarCaractereStergere <= stringBuilder.length()) {
            stringBuilder.delete(pozitieStergere, pozitieStergere + numarCaractereStergere);
            System.out.println("Șirul după ștergere: " + stringBuilder.toString());
        } else {
            System.out.println("Poziția de ștergere sau numărul de caractere sunt invalide!");
        }

        scanner.close();
    }
}
