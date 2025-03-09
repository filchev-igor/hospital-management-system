package lt.dev.hospitalmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DoctorDTO {
    private UUID id;
    private String name;
    private String specialty;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}