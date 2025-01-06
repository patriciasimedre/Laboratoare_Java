package ex2;

import java.util.ArrayList;
import java.util.List;

public class PerecheNumere {
    // Variabile membre private
    private int x;
    private int y;

    // Constructor fără parametri (necesar pentru Jackson)
    public PerecheNumere() {
        // Jackson folosește acest constructor la serializare/deserializare
    }

    // Constructor cu parametri
    public PerecheNumere(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter pentru x
    public int getX() {
        return x;
    }

    // Setter pentru x
    public void setX(int x) {
        this.x = x;
    }

    // Getter pentru y
    public int getY() {
        return y;
    }

    // Setter pentru y
    public void setY(int y) {
        this.y = y;
    }

    // Metodă care returnează true dacă x și y sunt numere consecutive în șirul Fibonacci
    public boolean suntConsecutiveFibonacci() {
        // Dacă oricare dintre x sau y <= 0, considerăm că nu fac parte din Fibonacci
        if(x <= 0 || y <= 0) {
            return false;
        }

        // Generăm un șir Fibonacci până depășim max(x,y)
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);

        int i = 2;
        while(true) {
            int urm = fib.get(i-1) + fib.get(i-2);
            fib.add(urm);
            if(urm > Math.max(x, y)) {
                break;
            }
            i++;
        }

        // Căutăm pozițiile lui x și y în listă
        int indexX = fib.indexOf(x);
        int indexY = fib.indexOf(y);

        if(indexX == -1 || indexY == -1) {
            // Dacă vreunul dintre x sau y nu există în secvența generată, return false
            return false;
        }

        // Verificăm dacă sunt consecutivi
        return Math.abs(indexX - indexY) == 1;
    }

    // Metodă care returnează CMMC (cel mai mic multiplu comun) al lui x și y
    public int cmmc() {
        // Formula: x * y / gcd(x, y)
        return (x * y) / gcd(x, y);
    }

    // Metodă care returnează true dacă x și y au aceeași sumă a cifrelor
    public boolean aceeasiSumaCifre() {
        return sumaCifre(x) == sumaCifre(y);
    }

    // Metodă care returnează true dacă x și y au același număr de cifre pare
    public boolean acelasiNumarCifrePare() {
        return numarCifrePare(x) == numarCifrePare(y);
    }

    // Suprascrierea metodei toString() din Object
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Metodă privată pentru cel mai mare divizor comun (GCD)
    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Metodă privată pentru calculul sumei cifrelor
    private int sumaCifre(int n) {
        n = Math.abs(n);
        int suma = 0;
        while(n > 0) {
            suma += n % 10;
            n /= 10;
        }
        return suma;
    }

    // Metodă privată pentru numărul de cifre pare
    private int numarCifrePare(int n) {
        n = Math.abs(n);
        int count = 0;
        while(n > 0) {
            int c = n % 10;
            if(c % 2 == 0) {
                count++;
            }
            n /= 10;
        }
        return count;
    }
}