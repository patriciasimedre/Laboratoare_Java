package com.example.Laborator11_Java.repository;

import com.example.Laborator11_Java.entity.Eveniment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenimentRepository extends JpaRepository<Eveniment, Long> {
}

// http://localhost:8080/api/evenimente