package com.example.antony;

import com.example.antony.model.Boat;
import com.example.antony.repository.BoatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest(showSql = true)
public class JpaModelTest {

    @Autowired
    private BoatRepository boatRepository;

    @Test
    public void whenSaved_thenFindByName() {

        final String name = "Is it a boat or a plane?";

        Boat boat = new Boat();
        boat.setName(name);
        boatRepository.save(boat);
        assertTrue(boatRepository.findAll().stream().anyMatch(b -> b.getName().equalsIgnoreCase(name)));
    }
}
