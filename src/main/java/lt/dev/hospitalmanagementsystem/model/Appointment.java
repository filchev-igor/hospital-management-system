package lt.dev.hospitalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an appointment in the hospital management system.
 */
@Getter
@Setter
@Entity
@Table(name = "appointments")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @XmlElement
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @XmlElement
    private Doctor doctor;

    @XmlElement
    private LocalDateTime appointmentDate;

    @Column(name = "creation_date", nullable = false)
    @XmlElement
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false)
    @XmlElement
    private LocalDateTime updateDate;

    /**
     * Sets creation and update dates before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    /**
     * Updates the update date before updating the entity.
     */
    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}