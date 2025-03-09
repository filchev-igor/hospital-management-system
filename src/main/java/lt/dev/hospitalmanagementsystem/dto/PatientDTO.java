package lt.dev.hospitalmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PatientDTO {
    private UUID id;
    private String name;
    private LocalDate dob;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}