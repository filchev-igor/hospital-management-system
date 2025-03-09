package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.dto.DoctorDTO;
import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing doctors in the hospital management system.
 * Provides endpoints for retrieving, adding, updating, and deleting doctors.
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param doctorRepository the repository for accessing doctor data
     */
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Retrieves all doctors.
     *
     * @return a list of DoctorDTOs representing all doctors
     */
    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a doctor by ID.
     *
     * @param id the UUID of the doctor to retrieve
     * @return the DoctorDTO for the specified ID, or null if not found
     */
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable UUID id) {
        return doctorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Adds a new doctor to the database.
     *
     * @param doctor the doctor to add
     * @return the saved doctor as a DTO
     */
    @PostMapping
    public DoctorDTO addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDTO(savedDoctor);
    }

    /**
     * Updates an existing doctor by ID.
     *
     * @param id the UUID of the doctor to update
     * @param doctor the updated doctor data
     * @return the updated DoctorDTO, or null if not found
     */
    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable UUID id, @RequestBody Doctor doctor) {
        if (doctorRepository.existsById(id)) {
            doctor.setId(id);
            Doctor updatedDoctor = doctorRepository.save(doctor);
            return convertToDTO(updatedDoctor);
        }
        return null;
    }

    /**
     * Deletes a doctor by ID.
     *
     * @param id the UUID of the doctor to delete
     * @return a string confirming the deletion
     */
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable UUID id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return "Doctor with ID " + id + " deleted successfully";
        } else {
            return "Doctor with ID " + id + " not found";
        }
    }

    /**
     * Converts a Doctor entity to a DoctorDTO.
     *
     * @param doctor the Doctor entity to convert
     * @return the converted DoctorDTO
     */
    private DoctorDTO convertToDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        if (doctor != null) {
            dto.setId(doctor.getId());
            dto.setName(doctor.getName());
            dto.setSpecialty(doctor.getSpecialty());
            dto.setCreationDate(doctor.getCreationDate());
            dto.setUpdateDate(doctor.getUpdateDate());
        }
        return dto;
    }
}