package com.example.Carte.repository;

import com.example.Carte.entitate.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutorContainingIgnoreCase(String autor);
}
// docker exec -it mysql_carti mysql -u root -p