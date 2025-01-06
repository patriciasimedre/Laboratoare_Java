package com.example.masini_JPA.entitate;

import jakarta.persistence.*;

@Entity
@Table(name = "masina")
public class Masina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numar_inmatriculare", nullable = false, unique = true)
    private String numarInmatriculare;

    @Column(name = "marca")
    private String marca;

    @Column(name = "an_fabricatie")
    private int anFabricatie;

    @Column(name = "culoare")
    private String culoare;

    @Column(name = "numar_kilometri")
    private int numarKilometri;

    // Getters și Setters
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
