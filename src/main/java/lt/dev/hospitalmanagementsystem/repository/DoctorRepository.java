package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Doctor entities.
 * Extends JpaRepository to provide basic CRUD operations.
 */
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    /**
     * Retrieves all doctors with eager loading of related entities (if any).
     *
     * @return a list of all doctors
     */
    @EntityGraph(attributePaths = {}) // Add relationships if applicable (e.g., {"appointments"})
    List<Doctor> findAll();
}