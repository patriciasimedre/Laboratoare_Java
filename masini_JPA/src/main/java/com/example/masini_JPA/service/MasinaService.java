package com.example.masini_JPA.service;

import com.example.masini_JPA.entitate.Masina;
import com.example.masini_JPA.repository.MasinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaService {

    private final MasinaRepository masinaRepository;

    public MasinaService(MasinaRepository masinaRepository) {
        this.masinaRepository = masinaRepository;
    }

    public Masina adaugaMasina(Masina masina) {
        return masinaRepository.save(masina);
    }

    public void stergeMasinaDupaNumar(String numarInmatriculare) {
        Masina masina = masinaRepository.findByNumarInmatriculare(numarInmatriculare);
        if (masina != null) {
            masinaRepository.delete(masina);
        }
    }

    public Masina cautaMasinaDupaNumar(String numarInmatriculare) {
        return masinaRepository.findByNumarInmatriculare(numarInmatriculare);
    }

    public List<Masina> toateMasinile() {
        return masinaRepository.findAll();
    }

    public int numaraMasiniDupaMarca(String marca) {
        return masinaRepository.numaraMasinileDupaMarca(marca);
    }

    public int numaraMasiniSub100000Km() {
        return masinaRepository.numaraMasinileSub100000Km();
    }

    public List<Masina> masiniMaiNoiDe5Ani() {
        return masinaRepository.findMasiniMaiNoiDe5Ani();
    }
}