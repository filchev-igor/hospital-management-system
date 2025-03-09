package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Patient entities.
 * Extends JpaRepository to provide basic CRUD operations.
 */
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Retrieves all patients with eager loading of related entities (if any).
     *
     * @return a list of all patients
     */
    @EntityGraph(attributePaths = {}) // Add relationships if applicable (e.g., {"appointments"})
    List<Patient> findAll();
}