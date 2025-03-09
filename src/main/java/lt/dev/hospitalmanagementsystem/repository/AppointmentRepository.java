package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findAll();
}