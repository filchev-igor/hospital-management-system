package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.dto.AppointmentDTO;
import lt.dev.hospitalmanagementsystem.dto.DoctorDTO;
import lt.dev.hospitalmanagementsystem.dto.PatientDTO;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AppointmentDTO addAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

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