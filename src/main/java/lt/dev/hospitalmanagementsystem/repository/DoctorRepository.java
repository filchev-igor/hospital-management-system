package lt.dev.hospitalmanagementsystem.repository;

import lt.dev.hospitalmanagementsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
