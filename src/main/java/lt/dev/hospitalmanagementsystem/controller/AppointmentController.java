package lt.dev.hospitalmanagementsystem.controller;

import jakarta.xml.bind.JAXBException;
import lt.dev.hospitalmanagementsystem.dto.AppointmentDTO;
import lt.dev.hospitalmanagementsystem.dto.DoctorDTO;
import lt.dev.hospitalmanagementsystem.dto.PatientDTO;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.AppointmentRepository;
import lt.dev.hospitalmanagementsystem.service.XmlExportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing appointments in the hospital management system.
 * Provides endpoints for retrieving, adding appointments, and exporting to XML.
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final XmlExportService xmlExportService;

    /**
     * Constructor for dependency injection.
     *
     * @param appointmentRepository the repository for accessing appointment data
     * @param xmlExportService the service for exporting appointments to XML
     */
    public AppointmentController(AppointmentRepository appointmentRepository, XmlExportService xmlExportService) {
        this.appointmentRepository = appointmentRepository;
        this.xmlExportService = xmlExportService;
    }

    /**
     * Retrieves appointments filtered by doctorId and/or patientId.
     *
     * @param doctorId the UUID of the doctor to filter by (optional)
     * @param patientId the UUID of the patient to filter by (optional)
     * @return a list of AppointmentDTOs representing the filtered appointments
     */
    @GetMapping
    public List<AppointmentDTO> getAllAppointments(
            @RequestParam(value = "doctorId", required = false) UUID doctorId,
            @RequestParam(value = "patientId", required = false) UUID patientId) {
        List<Appointment> appointments = fetchAppointments(doctorId, patientId);
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Exports appointments to XML, filtered by doctorId and/or patientId.
     *
     * @param doctorId the UUID of the doctor to filter by (optional)
     * @param patientId the UUID of the patient to filter by (optional)
     * @return the file path of the generated XML file
     * @throws JAXBException if XML marshalling fails
     */
    @GetMapping("/export")
    public String exportAppointmentsToXml(
            @RequestParam(value = "doctorId", required = false) UUID doctorId,
            @RequestParam(value = "patientId", required = false) UUID patientId) throws JAXBException {
        List<Appointment> appointments = fetchAppointments(doctorId, patientId);
        return xmlExportService.exportAppointmentsToXml(appointments);
    }

    /**
     * Adds a new appointment to the database.
     *
     * @param appointment the appointment to add
     * @return the saved appointment as a DTO
     */
    @PostMapping
    public AppointmentDTO addAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

    /**
     * Fetches appointments based on doctorId and/or patientId.
     *
     * @param doctorId the UUID of the doctor to filter by (optional)
     * @param patientId the UUID of the patient to filter by (optional)
     * @return a list of filtered appointments
     */
    private List<Appointment> fetchAppointments(UUID doctorId, UUID patientId) {
        if (doctorId != null && patientId != null) {
            return appointmentRepository.findByDoctorIdAndPatientId(doctorId, patientId);
        } else if (doctorId != null) {
            return appointmentRepository.findByDoctorId(doctorId);
        } else if (patientId != null) {
            return appointmentRepository.findByPatientId(patientId);
        } else {
            return appointmentRepository.findAll();
        }
    }

    /**
     * Converts an Appointment entity to an AppointmentDTO.
     *
     * @param appointment the Appointment entity to convert
     * @return the converted AppointmentDTO
     */
    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setPatient(convertToPatientDTO(appointment.getPatient()));
        dto.setDoctor(convertToDoctorDTO(appointment.getDoctor()));
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setCreationDate(appointment.getCreationDate());
        dto.setUpdateDate(appointment.getUpdateDate());
        return dto;
    }

    /**
     * Converts a Patient entity to a PatientDTO.
     *
     * @param patient the Patient entity to convert
     * @return the converted PatientDTO
     */
    private PatientDTO convertToPatientDTO(Patient patient) {
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

    /**
     * Converts a Doctor entity to a DoctorDTO.
     *
     * @param doctor the Doctor entity to convert
     * @return the converted DoctorDTO
     */
    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
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