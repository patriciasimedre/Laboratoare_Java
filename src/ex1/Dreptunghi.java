/*
1. Se cere să se scrie un program Java care să calculeze şi să afişeze perimetru şi aria unui
dreptunghi. Valorile pentru lungime şi lățime se citesc de la tastatura. Sa se adauge un break
point pe prima linie care citește valoarea unei laturi si din acel punct să se ruleze programul
linie cu linie urmărind valorile variabilelor în memorie.
 */

package ex1;

import java.util.Scanner;

public class Dreptunghi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citirea lungimii
        System.out.print("Introduceți lungimea dreptunghiului: ");
        double lungime = scanner.nextDouble(); // Setează breakpoint aici

        // Citirea lățimii
        System.out.print("Introduceți lățimea dreptunghiului: ");
        double latime = scanner.nextDouble();

        // Calcularea perimetrului
        double perimetru = 2 * (lungime + latime);

        // Calcularea ariei
        double aria = lungime * latime;

        // Afișarea rezultatelor
        System.out.println("Perimetrul dreptunghiului este: " + perimetru);
        System.out.println("Aria dreptunghiului este: " + aria);

        scanner.close();
    }
}
