package com.example.masini.service;

import com.example.masini.entitate.Masina;
import com.example.masini.repository.MasinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasinaService {

    private static final Logger logger = LoggerFactory.getLogger(MasinaService.class);
    private final MasinaRepository masinaRepository;

    public MasinaService(MasinaRepository masinaRepository) {
        this.masinaRepository = masinaRepository;
    }

    public Masina adaugaMasina(Masina masina) {
        logger.info("Adăugare mașină: {}", masina);
        return masinaRepository.save(masina);
    }

    @Transactional
    public void stergeMasinaDupaNumar(String numarInmatriculare) {
        logger.info("Se încearcă ștergerea mașinii cu numărul de înmatriculare: {}", numarInmatriculare);

        Masina masina = masinaRepository.cautaDupaNumarInmatriculare(numarInmatriculare);

        if (masina != null) {
            masinaRepository.delete(masina);
            logger.info("Mașina cu numărul de înmatriculare {} a fost ștearsă cu succes.", numarInmatriculare);
        } else {
            logger.warn("Mașina cu numărul de înmatriculare {} nu a fost găsită în baza de date.", numarInmatriculare);
        }
    }

    public Masina cautaMasinaDupaNumar(String numarInmatriculare) {
        return masinaRepository.cautaDupaNumarInmatriculare(numarInmatriculare);
    }

    public List<Masina> toateMasinile() {
        return masinaRepository.gasesteToateMasinile();
    }

    public int numaraMasiniDupaMarca(String marca) {
        return masinaRepository.numaraMasinileDupaMarca(marca);
    }

    public int numaraMasiniSub100000Km() {
        return masinaRepository.numaraMasinileSub100000Km();
    }

    public List<Masina> masiniMaiNoiDe5Ani() {
        return masinaRepository.gasesteMasiniMaiNoiDe5Ani();
    }
}
