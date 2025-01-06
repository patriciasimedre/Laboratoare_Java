package teste; // Poți modifica numele pachetului după preferințe

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ex2.PerecheNumere;

// Clasa de test pentru PerecheNumere (Junit)
public class TestPerecheNumere {

    // Teste pentru metoda suntConsecutiveFibonacci()
    @Test
    void testFibonacci1() {
        PerecheNumere p = new PerecheNumere(3, 5);
        assertTrue(p.suntConsecutiveFibonacci());
    }

    @Test
    void testFibonacci2() {
        PerecheNumere p = new PerecheNumere(5, 8);
        assertTrue(p.suntConsecutiveFibonacci());
    }

    @Test
    void testFibonacci3() {
        PerecheNumere p = new PerecheNumere(4, 5);
        // 4 nu face parte din șirul Fibonacci standard, deci nu ar trebui să fie consecutive
        assertFalse(p.suntConsecutiveFibonacci());
    }

    // Teste pentru metoda cmmc()
    @Test
    void testCMMC1() {
        PerecheNumere p = new PerecheNumere(3, 5);
        // CMMC(3,5) = 15
        assertEquals(15, p.cmmc());
    }

    @Test
    void testCMMC2() {
        PerecheNumere p = new PerecheNumere(6, 8);
        // GCD(6,8)=2 => CMMC=(6*8)/2=24
        assertEquals(24, p.cmmc());
    }

    @Test
    void testCMMC3() {
        PerecheNumere p = new PerecheNumere(12, 18);
        // GCD(12,18)=6 => CMMC=(12*18)/6=36
        assertEquals(36, p.cmmc());
    }

    // Teste pentru metoda aceeasiSumaCifre()
    @Test
    void testAceeasiSumaCifre1() {
        PerecheNumere p = new PerecheNumere(12, 21);
        // Suma cifrelor 12=1+2=3, 21=2+1=3
        assertTrue(p.aceeasiSumaCifre());
    }

    @Test
    void testAceeasiSumaCifre2() {
        PerecheNumere p = new PerecheNumere(99, 18);
        // Suma cifrelor 99=9+9=18, 18=1+8=9 => 18 != 9
        assertFalse(p.aceeasiSumaCifre());
    }

    @Test
    void testAceeasiSumaCifre3() {
        PerecheNumere p = new PerecheNumere(123, 30);
        // 123->1+2+3=6, 30->3+0=3 => 6 != 3
        assertFalse(p.aceeasiSumaCifre());
    }

    // Teste pentru metoda acelasiNumarCifrePare()
    @Test
    void testAcelasiNumarCifrePare1() {
        PerecheNumere p = new PerecheNumere(24, 60);
        // 24->2 (par),4 (par) => 2 cifre pare
        // 60->6 (par),0 (par) => 2 cifre pare
        assertTrue(p.acelasiNumarCifrePare());
    }

    @Test
    void testAcelasiNumarCifrePare2() {
        PerecheNumere p = new PerecheNumere(246, 80);
        // 246->2 (par),4 (par),6 (par) => 3 cifre pare
        // 80->8 (par),0 (par) => 2 cifre pare
        assertFalse(p.acelasiNumarCifrePare());
    }

    @Test
    void testAcelasiNumarCifrePare3() {
        PerecheNumere p = new PerecheNumere(135, 531);
        // 135->1 (imp),3 (imp),5 (imp) => 0 cifre pare
        // 531->5 (imp),3 (imp),1 (imp) => 0 cifre pare
        assertTrue(p.acelasiNumarCifrePare());
    }
}
