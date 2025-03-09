package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Appointment entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    /**
     * Retrieves all appointments with eager loading of patient and doctor relationships.
     *
     * @return a list of all appointments
     */
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findAll();

    /**
     * Retrieves appointments for a specific doctor with eager loading of patient and doctor relationships.
     *
     * @param doctorId the UUID of the doctor to filter by (can be null)
     * @return a list of appointments for the specified doctor
     */
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByDoctorId(@Nullable UUID doctorId);

    /**
     * Retrieves appointments for a specific patient with eager loading of patient and doctor relationships.
     *
     * @param patientId the UUID of the patient to filter by (can be null)
     * @return a list of appointments for the specified patient
     */
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByPatientId(@Nullable UUID patientId);

    /**
     * Retrieves appointments for a specific doctor and patient with eager loading of relationships.
     *
     * @param doctorId the UUID of the doctor to filter by (can be null)
     * @param patientId the UUID of the patient to filter by (can be null)
     * @return a list of appointments matching the doctor and patient
     */
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByDoctorIdAndPatientId(@Nullable UUID doctorId, @Nullable UUID patientId);
}