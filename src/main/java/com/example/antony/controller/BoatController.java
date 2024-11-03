package com.example.antony.controller;

import com.example.antony.model.Boat;
import com.example.antony.service.BoatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boats")
public class BoatController {
    private final BoatService boatService;

    @Autowired
    public BoatController(final BoatService boatService) {
        this.boatService = boatService;
    }

    /**
     * Retrieves a list of all boats.
     *
     * @return a list of Boat objects representing all boats.
     */
    @GetMapping
    public List<Boat> getAllBoats() {
        return boatService.getAllBoats();
    }

    /**
     * Retrieves a boat by its unique ID.
     *
     * @param id the unique identifier of the boat.
     * @return a ResponseEntity containing the Boat if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoatById(final @PathVariable Long id) {
        return boatService.getBoatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a boat by its name.
     *
     * @param name the name of the boat to retrieve.
     * @return a ResponseEntity containing the Boat if found, or a 404 Not Found response if not.
     */
    @GetMapping("/")
    public ResponseEntity<Boat> getBoatByName(final @RequestParam String name) {
        return boatService.getBoatByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new boat entry.
     *
     * @param boat the Boat object to be created.
     * @return the created Boat object.
     */
    @PostMapping
    public Boat createBoat(final @Valid @RequestBody Boat boat) {
        return boatService.saveBoat(boat);
    }

    /**
     * Deletes a boat by its unique ID.
     *
     * @param id the unique identifier of the boat to delete.
     * @return a ResponseEntity with a 204 No Content status if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoat(final @PathVariable Long id) {
        boatService.deleteBoat(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing boat's details or creates a new boat if the ID does not exist.
     *
     * @param newBoat the Boat object containing updated information.
     * @param id      the unique identifier of the boat to update or create.
     * @return the updated or newly created Boat object.
     */
    @PutMapping("/{id}")
    public Boat replaceBoat(@RequestBody Boat newBoat, @PathVariable Long id) {
        return boatService.getBoatById(id)
                .map(boat -> {
                    boat.setName(newBoat.getName());
                    return boatService.saveBoat(boat);
                })
                .orElseGet(() -> boatService.saveBoat(newBoat));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
