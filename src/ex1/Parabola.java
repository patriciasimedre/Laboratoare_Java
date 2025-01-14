package ex1;

import java.util.*;
import java.io.*;

public class Parabola {
    private int a, b, c;

    // Constructor cu 3 parametri
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Metodă pentru calcularea vârfului parabolei
    public double[] calculeazaVarf() {
        double x = -b / (2.0 * a);
        double y = (-b * b + 4.0 * a * c) / (4.0 * a);
        return new double[]{x, y};
    }

    // Suprascrierea metodei toString pentru afișarea parabolei
    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    // Metodă pentru coordonatele mijlocului segmentului dintre vârfuri
    public double[] mijlocSegment(Parabola altaParabola) {
        double[] varf1 = this.calculeazaVarf();
        double[] varf2 = altaParabola.calculeazaVarf();
        double xMijloc = (varf1[0] + varf2[0]) / 2.0;
        double yMijloc = (varf1[1] + varf2[1]) / 2.0;
        return new double[]{xMijloc, yMijloc};
    }

    // Metodă pentru lungimea segmentului dintre două vârfuri
    public double lungimeSegment(Parabola altaParabola) {
        double[] varf1 = this.calculeazaVarf();
        double[] varf2 = altaParabola.calculeazaVarf();
        return Math.hypot(varf2[0] - varf1[0], varf2[1] - varf1[1]);
    }

    // Metodă statică pentru mijlocul segmentului dintre două parabolă
    public static double[] mijlocSegment(Parabola parabola1, Parabola parabola2) {
        return parabola1.mijlocSegment(parabola2);
    }

    // Metodă statică pentru lungimea segmentului dintre două parabolă
    public static double lungimeSegment(Parabola parabola1, Parabola parabola2) {
        return parabola1.lungimeSegment(parabola2);
    }
}
