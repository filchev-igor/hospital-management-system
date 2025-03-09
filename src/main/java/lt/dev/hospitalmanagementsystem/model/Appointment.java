package lt.dev.hospitalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "appointments")
@JsonInclude(JsonInclude.Include.NON_NULL)  // Only include non-null fields in JSON
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference  // Prevent circular reference for bidirectional relationship
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonBackReference  // Prevent circular reference for bidirectional relationship
    private Doctor doctor;

    private LocalDateTime appointmentDate;

}
