package com.example.antony.controller;

import com.example.antony.model.Boat;
import com.example.antony.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
public class BoatController {
    private final BoatService boatService;

    @Autowired
    public BoatController(final BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping
    public List<Boat> getAllBoats() {
        return boatService.getAllBoats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoatById(final @PathVariable Long id) {
        return boatService.getBoatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<Boat> getBoatByName(final @RequestParam String name) {
        return boatService.getBoatByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Boat createBoat(final @RequestBody Boat boat) {
        return boatService.saveBoat(boat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoat(final @PathVariable Long id) {
        boatService.deleteBoat(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    Boat replaceboat(@RequestBody Boat newBoat, @PathVariable Long id) {

        return boatService.getBoatById(id)
                .map(boat -> {
                    boat.setName(newBoat.getName());
                    return boatService.saveBoat(boat);
                })
                .orElseGet(() -> {
                    return boatService.saveBoat(newBoat);
                });
    }
}
