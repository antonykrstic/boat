package com.example.antony.repository;

import com.example.antony.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the Boat entity.
 * Extends JpaRepository to provide basic CRUD operations, and includes a custom query
 * method for finding a boat by its name.
 */
@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    /**
     * Finds a boat by its name using a native SQL query.
     *
     * @param name the name of the boat to search for.
     * @return an Optional containing the Boat if found, or empty if not found.
     */
    @Query(nativeQuery = true, value = "SELECT b.id, b.name FROM boat b WHERE b.name = :name")
    Optional<Boat> findBoatByName(final @Param("name") String name);
}
