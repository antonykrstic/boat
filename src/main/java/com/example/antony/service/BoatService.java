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
    public BoatService(final BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoatById(final Long id) {
        return boatRepository.findById(id);
    }

    public Optional<Boat> getBoatByName(final String name) {
        return boatRepository.findBoatByName(name);
    }

    public Boat saveBoat(final Boat boat) {
        return boatRepository.save(boat);
    }

    public void deleteBoat(final Long id) {
        boatRepository.deleteById(id);
    }
}
