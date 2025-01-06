package com.example.Laborator11_Java.controller;

import com.example.Laborator11_Java.entity.Eveniment;
import com.example.Laborator11_Java.service.EvenimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evenimente") // Mapează toate rutele sub /api/evenimente
public class EvenimenteController {

    @Autowired
    private EvenimentService evenimentService;

    @GetMapping
    public List<Eveniment> getAllEvenimente() {
        return evenimentService.getAllEvenimente();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eveniment> getEvenimentById(@PathVariable Long id) {
        return evenimentService.getEvenimentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Eveniment createEveniment(@RequestBody Eveniment eveniment) {
        return evenimentService.createEveniment(eveniment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eveniment> updateEveniment(@PathVariable Long id, @RequestBody Eveniment evenimentNou) {
        try {
            return ResponseEntity.ok(evenimentService.updateEveniment(id, evenimentNou));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEveniment(@PathVariable Long id) {
        evenimentService.deleteEveniment(id);
        return ResponseEntity.noContent().build();
    }
}
