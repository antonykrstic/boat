package com.example.antony.repository;

import com.example.antony.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Query(nativeQuery = true, value = "SELECT b.id, b.name FROM boat b where b.name=:name")
    Optional<Boat> findBoatByName(final @Param("name") String name);
}
