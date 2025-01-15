package com.example.Carte.controller;

import com.example.Carte.entitate.Carte;
import com.example.Carte.service.CarteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarteWebController {

    private final CarteService carteService;

    public CarteWebController(CarteService carteService) {
        this.carteService = carteService;
    }

    // Afișează lista cărților direct la accesarea paginii "/lista-carti"
    @GetMapping("/lista-carti")
    public String listaCarti(Model model) {
        List<Carte> carti = carteService.toateCartile();
        model.addAttribute("carti", carti);
        model.addAttribute("mesaj", "");
        return "carti";
    }

    @PostMapping("/operatii")
    public String operatii(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titlu,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String adauga,
            @RequestParam(required = false) String modifica,
            @RequestParam(required = false) String sterge,
            @RequestParam(required = false) String filtreaza,
            Model model) {

        String mesaj = "";
        List<Carte> carti = carteService.toateCartile();

        if (adauga != null) {
            if (isbn == null || titlu == null || autor == null || isbn.isBlank() || titlu.isBlank() || autor.isBlank()) {
                mesaj = "Toate câmpurile sunt obligatorii pentru a adăuga o carte!";
            } else {
                Carte carte = new Carte(isbn, titlu, autor);
                mesaj = carteService.adaugaCarte(carte);
            }
        } else if (modifica != null) {
            if (isbn == null || isbn.isBlank()) {
                mesaj = "ISBN-ul este obligatoriu pentru modificare!";
            } else {
                Carte carte = new Carte(isbn, titlu, autor);
                mesaj = carteService.modificaCarte(carte);
            }
        } else if (sterge != null) {
            if (isbn == null || isbn.isBlank()) {
                mesaj = "ISBN-ul este obligatoriu pentru ștergere!";
            } else {
                carteService.stergeCarte(isbn);
                mesaj = "Cartea a fost ștearsă!";
            }
        } else if (filtreaza != null) {
            if (autor == null || autor.isBlank()) {
                mesaj = "Toate cărțile sunt afișate!";
                carti = carteService.toateCartile();
            } else {
                carti = carteService.filtreazaDupaAutor(autor);
                mesaj = "Cărțile următoare aparțin autorului " + autor;
            }
        }

        model.addAttribute("carti", carti);
        model.addAttribute("mesaj", mesaj);
        return "carti";
    }
}
