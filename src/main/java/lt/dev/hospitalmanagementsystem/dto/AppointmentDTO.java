package lt.dev.hospitalmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID id;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private LocalDateTime appointmentDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}