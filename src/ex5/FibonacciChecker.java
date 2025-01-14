/*
5. Să se scrie un program care generează aleatoriu un număr întreg cuprins între 0 și 20.
Programul va determina dacă numărul aparține șirului lui Fobonacci.
 */

package ex5;

import java.util.Random;

public class FibonacciChecker {
    public static void main(String[] args) {
        // Generarea unui număr aleatoriu între 0 și 20
        Random random = new Random();
        int numar = random.nextInt(21); // nextInt(21) generează numere între 0 și 20

        // Afișarea numărului generat
        System.out.println("Numărul generat este: " + numar);

        // Verificarea dacă numărul aparține șirului lui Fibonacci
        if (esteFibonacci(numar)) {
            System.out.println("Numărul " + numar + " aparține șirului lui Fibonacci.");
        } else {
            System.out.println("Numărul " + numar + " nu aparține șirului lui Fibonacci.");
        }
    }

    // Funcție pentru verificarea dacă un număr aparține șirului lui Fibonacci
    public static boolean esteFibonacci(int n) {
        if (n == 0 || n == 1) {
            return true; // 0 și 1 sunt în mod explicit în șirul lui Fibonacci
        }

        int a = 0, b = 1;
        while (b < n) {
            int temp = b;
            b = a + b;
            a = temp;
        }

        return b == n; // Dacă am găsit numărul în șir, returnăm true
    }
}

// Șirul lui Fibonacci până la 20: 0,1,1,2,3,5,8,13