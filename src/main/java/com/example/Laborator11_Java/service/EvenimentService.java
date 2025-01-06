package com.example.Laborator11_Java.service;

import com.example.Laborator11_Java.entity.Eveniment;
import com.example.Laborator11_Java.repository.EvenimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenimentService {

    @Autowired
    private EvenimentRepository evenimentRepository;

    public List<Eveniment> getAllEvenimente() {
        return evenimentRepository.findAll();
    }

    public Optional<Eveniment> getEvenimentById(Long id) {
        return evenimentRepository.findById(id);
    }

    public Eveniment createEveniment(Eveniment eveniment) {
        return evenimentRepository.save(eveniment);
    }

    public Eveniment updateEveniment(Long id, Eveniment evenimentNou) {
        return evenimentRepository.findById(id)
                .map(eveniment -> {
                    eveniment.setDenumire(evenimentNou.getDenumire());
                    eveniment.setLocatie(evenimentNou.getLocatie());
                    eveniment.setDataTimp(evenimentNou.getDataTimp());
                    eveniment.setPretBilet(evenimentNou.getPretBilet());
                    return evenimentRepository.save(eveniment);
                }).orElseThrow(() -> new RuntimeException("Evenimentul nu a fost găsit!"));
    }

    public void deleteEveniment(Long id) {
        evenimentRepository.deleteById(id);
    }
}
