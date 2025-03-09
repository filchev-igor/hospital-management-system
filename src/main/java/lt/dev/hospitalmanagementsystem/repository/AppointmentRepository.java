package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findAll();

    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByDoctorId(@Nullable UUID doctorId);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByPatientId(@Nullable UUID patientId);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByDoctorIdAndPatientId(@Nullable UUID doctorId, @Nullable UUID patientId);
}