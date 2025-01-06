package com.example.masini.repository;

import com.example.masini.entitate.Masina;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasinaRepository extends CrudRepository<Masina, Long> {

    @Query("SELECT * FROM masina WHERE numar_inmatriculare = :numarInmatriculare")
    Masina cautaDupaNumarInmatriculare(String numarInmatriculare);

    @Query("SELECT * FROM masina")
    List<Masina> gasesteToateMasinile();

    @Query("SELECT COUNT(*) FROM masina WHERE marca = :marca")
    int numaraMasinileDupaMarca(String marca);

    @Query("SELECT COUNT(*) FROM masina WHERE numar_kilometri < 100000")
    int numaraMasinileSub100000Km();

    @Query("SELECT * FROM masina WHERE YEAR(CURDATE()) - an_fabricatie <= 5")
    List<Masina> gasesteMasiniMaiNoiDe5Ani();

    // Adăugăm metoda pentru ștergere
    @Modifying
    @Query("DELETE FROM masina WHERE numar_inmatriculare = :numarInmatriculare")
    void stergeDupaNumarInmatriculare(String numarInmatriculare);
}
