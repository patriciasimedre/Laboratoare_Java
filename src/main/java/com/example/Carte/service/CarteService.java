package com.example.Carte.service;

import com.example.Carte.entitate.Carte;
import com.example.Carte.repository.CarteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteService {

    private final CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public List<Carte> toateCartile() {
        return carteRepository.findAll();
    }

    public String adaugaCarte(Carte carte) {
        if (carteRepository.existsById(carte.getIsbn())) {
            return "Cartea cu ISBN-ul " + carte.getIsbn() + " există deja!";
        }
        carteRepository.save(carte);
        return "Adăugare realizată cu succes!";
    }

    public String modificaCarte(Carte carte) {
        if (!carteRepository.existsById(carte.getIsbn())) {
            return "Nu există nicio carte cu ISBN-ul " + carte.getIsbn();
        }
        carteRepository.save(carte);
        return "Cartea cu ISBN-ul " + carte.getIsbn() + " a fost modificată!";
    }

    public void stergeCarte(String isbn) {
        carteRepository.deleteById(isbn);
    }

    public List<Carte> filtreazaDupaAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            return carteRepository.findAll();
        }
        return carteRepository.findByAutorContainingIgnoreCase(autor);
    }

    @PostConstruct
    public void verificaCarti() {
        System.out.println("Carti în baza de date: " + carteRepository.findAll());
    }
}
