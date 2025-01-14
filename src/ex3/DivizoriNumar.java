/*
3. Să se scrie un program care citește un număr n natural de la tastatură și afișează toți
divizorii acestuia pe ecran. Dacă numărul este prim se va afișa un mesaj corespunzător.
 */

package ex3;

import java.util.Scanner;

public class DivizoriNumar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea numărului de la tastatură
        System.out.print("Introduceți un număr natural: ");
        int n = scanner.nextInt();

        // Verificarea și afișarea divizorilor
        boolean estePrim = true;
        System.out.println("Divizorii numărului " + n + " sunt:");

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                if (i != 1 && i != n) {
                    estePrim = false; // Dacă există un divizor diferit de 1 și n, nu este prim
                }
            }
        }
        System.out.println(); // Linie nouă după lista de divizori

        // Verificarea dacă numărul este prim
        if (estePrim && n > 1) {
            System.out.println("Numărul " + n + " este prim.");
        } else if (n > 1) {
            System.out.println("Numărul " + n + " nu este prim.");
        } else {
            System.out.println("Numărul trebuie să fie mai mare decât 1 pentru a fi considerat prim.");
        }

        scanner.close();
    }
}
