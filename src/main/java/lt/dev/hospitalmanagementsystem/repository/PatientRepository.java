package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
