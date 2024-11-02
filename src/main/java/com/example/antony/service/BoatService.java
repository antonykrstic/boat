package com.example.antony.service;

import com.example.antony.model.Boat;
import com.example.antony.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    private final BoatRepository boatRepository;

    @Autowired
    public BoatService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoatById(Long id) {
        return boatRepository.findById(id);
    }

    public Boat saveBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    public void deleteBoat(Long id) {
        boatRepository.deleteById(id);
    }
}