package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.dto.PatientDTO;
import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing patients in the hospital management system.
 * Provides endpoints for retrieving, adding, updating, and deleting patients.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param patientRepository the repository for accessing patient data
     */
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieves all patients.
     *
     * @return a list of PatientDTOs representing all patients
     */
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a patient by ID.
     *
     * @param id the UUID of the patient to retrieve
     * @return the PatientDTO for the specified ID, or null if not found
     */
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable UUID id) {
        return patientRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Adds a new patient to the database.
     *
     * @param patient the patient to add
     * @return the saved patient as a DTO
     */
    @PostMapping
    public PatientDTO addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return convertToDTO(savedPatient);
    }

    /**
     * Updates an existing patient by ID.
     *
     * @param id the UUID of the patient to update
     * @param patient the updated patient data
     * @return the updated PatientDTO, or null if not found
     */
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable UUID id, @RequestBody Patient patient) {
        if (patientRepository.existsById(id)) {
            patient.setId(id);
            Patient updatedPatient = patientRepository.save(patient);
            return convertToDTO(updatedPatient);
        }
        return null;
    }

    /**
     * Deletes a patient by ID.
     *
     * @param id the UUID of the patient to delete
     * @return a string confirming the deletion
     */
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable UUID id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return "Patient with ID " + id + " deleted successfully";
        } else {
            return "Patient with ID " + id + " not found";
        }
    }

    /**
     * Converts a Patient entity to a PatientDTO.
     *
     * @param patient the Patient entity to convert
     * @return the converted PatientDTO
     */
    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        if (patient != null) {
            dto.setId(patient.getId());
            dto.setName(patient.getName());
            dto.setDob(patient.getDob());
            dto.setCreationDate(patient.getCreationDate());
            dto.setUpdateDate(patient.getUpdateDate());
        }
        return dto;
    }
}