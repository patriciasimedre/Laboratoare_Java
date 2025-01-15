package org.example;

import java.time.LocalDate;

public class Angajat {
    private String numele;
    private String postul;
    private LocalDate data_angajarii;
    private float salariul;

    // Constructor fără parametri (obligatoriu pentru Jackson)
    public Angajat() {
    }

    // Constructor cu parametri
    public Angajat(String numele, String postul, LocalDate data_angajarii, float salariu) {
        this.numele = numele;
        this.postul = postul;
        this.data_angajarii = data_angajarii;
        this.salariul = salariu;
    }

    // Getteri și setteri
    public String getNumele() {
        return numele;
    }

    public void setNumele(String numele) {
        this.numele = numele;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public LocalDate getDataAngajarii() {
        return data_angajarii;
    }

    public void setDataAngajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public float getSalariu() {
        return salariul;
    }

    public void setSalariu(float salariul) {
        this.salariul = salariul;
    }

    // Suprascrierea metodei toString()
    @Override
    public String toString() {
        return numele + ", " + postul + ", " + data_angajarii + ", " + salariul;
    }
}