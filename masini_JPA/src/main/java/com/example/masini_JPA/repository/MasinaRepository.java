package com.example.masini_JPA.repository;

import com.example.masini_JPA.entitate.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MasinaRepository extends JpaRepository<Masina, Long> {

    Masina findByNumarInmatriculare(String numarInmatriculare);

    @Query("SELECT COUNT(m) FROM Masina m WHERE m.marca = :marca")
    int numaraMasinileDupaMarca(String marca);

    @Query("SELECT COUNT(m) FROM Masina m WHERE m.numarKilometri < 100000")
    int numaraMasinileSub100000Km();

    @Query("SELECT m FROM Masina m WHERE YEAR(CURRENT_DATE) - m.anFabricatie <= 5")
    List<Masina> findMasiniMaiNoiDe5Ani();
}
