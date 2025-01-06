package com.example.masini.entitate;

import org.springframework.data.annotation.Id;

public class Masina {
    @Id
    private Long id;
    private String numarInmatriculare;
    private String marca;
    private int anFabricatie;
    private String culoare;
    private int numarKilometri;

    // Getteri și Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(int anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getNumarKilometri() {
        return numarKilometri;
    }

    public void setNumarKilometri(int numarKilometri) {
        this.numarKilometri = numarKilometri;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "id=" + id +
                ", numarInmatriculare='" + numarInmatriculare + '\'' +
                ", marca='" + marca + '\'' +
                ", anFabricatie=" + anFabricatie +
                ", culoare='" + culoare + '\'' +
                ", numarKilometri=" + numarKilometri +
                '}';
    }
}
