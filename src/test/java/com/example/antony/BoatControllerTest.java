package com.example.antony;

import com.example.antony.controller.BoatController;
import com.example.antony.model.Boat;
import com.example.antony.service.BoatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BoatControllerTest {

    @Mock
    private BoatService boatService;

    @InjectMocks
    private BoatController boatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBoats() {
        Boat boat1 = new Boat(1L, "Boat1");
        Boat boat2 = new Boat(2L, "Boat2");
        when(boatService.getAllBoats()).thenReturn(Arrays.asList(boat1, boat2));

        List<Boat> result = boatController.getAllBoats();

        assertEquals(2, result.size());
        assertEquals("Boat1", result.get(0).getName());
        verify(boatService, times(1)).getAllBoats();
    }

    @Test
    void testGetBoatById_Found() {
        Boat boat = new Boat(1L, "Boat1");
        when(boatService.getBoatById(1L)).thenReturn(Optional.of(boat));

        ResponseEntity<Boat> response = boatController.getBoatById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Boat1", response.getBody().getName());
        verify(boatService, times(1)).getBoatById(1L);
    }

    @Test
    void testGetBoatById_NotFound() {
        when(boatService.getBoatById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Boat> response = boatController.getBoatById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
        verify(boatService, times(1)).getBoatById(1L);
    }

    @Test
    void testGetBoatByName_Found() {
        Boat boat = new Boat(1L, "Boat1");
        when(boatService.getBoatByName("Boat1")).thenReturn(Optional.of(boat));

        ResponseEntity<Boat> response = boatController.getBoatByName("Boat1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Boat1", response.getBody().getName());
        verify(boatService, times(1)).getBoatByName("Boat1");
    }

    @Test
    void testGetBoatByName_NotFound() {
        when(boatService.getBoatByName("Boat1")).thenReturn(Optional.empty());

        ResponseEntity<Boat> response = boatController.getBoatByName("Boat1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
        verify(boatService, times(1)).getBoatByName("Boat1");
    }

    @Test
    void testCreateBoat() {
        Boat boat = new Boat(null, "Boat1");
        Boat savedBoat = new Boat(1L, "Boat1");
        when(boatService.saveBoat(any(Boat.class))).thenReturn(savedBoat);

        Boat result = boatController.createBoat(boat);

        assertEquals("Boat1", result.getName());
        assertEquals(1L, result.getId());
        verify(boatService, times(1)).saveBoat(boat);
    }

    @Test
    void testDeleteBoat() {
        doNothing().when(boatService).deleteBoat(1L);

        ResponseEntity<Void> response = boatController.deleteBoat(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(boatService, times(1)).deleteBoat(1L);
    }

    @Test
    void testReplaceBoat_UpdateExisting() {
        Boat existingBoat = new Boat(1L, "Old Boat");
        Boat newBoat = new Boat(null, "Updated Boat");
        when(boatService.getBoatById(1L)).thenReturn(Optional.of(existingBoat));
        when(boatService.saveBoat(any(Boat.class))).thenReturn(new Boat(1L, "Updated Boat"));

        Boat result = boatController.replaceBoat(newBoat, 1L);

        assertEquals("Updated Boat", result.getName());
        assertEquals(1L, result.getId());
        verify(boatService, times(1)).getBoatById(1L);
        verify(boatService, times(1)).saveBoat(existingBoat);
    }

    @Test
    void testReplaceBoat_CreateNew() {
        Boat newBoat = new Boat(null, "New Boat");
        when(boatService.getBoatById(1L)).thenReturn(Optional.empty());
        when(boatService.saveBoat(newBoat)).thenReturn(new Boat(1L, "New Boat"));

        Boat result = boatController.replaceBoat(newBoat, 1L);

        assertEquals("New Boat", result.getName());
        assertEquals(1L, result.getId());
        verify(boatService, times(1)).getBoatById(1L);
        verify(boatService, times(1)).saveBoat(newBoat);
    }
}
