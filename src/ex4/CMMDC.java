/*
4. Să se determine cmmdc a două numere naturale, a căror valoare maximă este 30.
Numerele vor fi generate aleatoriu cu ajutorul unui obiect de tip Random și metodei nextInt();
 */

package ex4;

import java.util.Random;

public class CMMDC {
    public static void main(String[] args) {
        // Generarea numerelor aleatorii
        Random random = new Random();
        int numar1 = random.nextInt(31); // Valoarea maximă 30 (nextInt(31) generează între 0 și 30)
        int numar2 = random.nextInt(31);

        // Afișarea numerelor generate
        System.out.println("Numerele generate sunt: " + numar1 + " și " + numar2);

        // Calcularea CMMDC folosind algoritmul lui Euclid
        int cmmdc = calculeazaCmmdc(numar1, numar2);

        // Afișarea rezultatului
        System.out.println("CMMDC-ul numerelor " + numar1 + " și " + numar2 + " este: " + cmmdc);
    }

    // Funcție pentru calcularea CMMDC folosind algoritmul lui Euclid
    public static int calculeazaCmmdc(int a, int b) {
        while (b != 0) {
            int rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }
}
