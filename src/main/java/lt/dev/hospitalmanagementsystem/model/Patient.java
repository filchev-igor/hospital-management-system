package lt.dev.hospitalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a patient in the hospital management system.
 */
@Getter
@Setter
@Entity
@Table(name = "patients")
@XmlRootElement(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private UUID id;

    @Column(nullable = false)
    @XmlElement
    private String name;

    @XmlElement
    private LocalDate dob;

    @Column(name = "creation_date", nullable = false)
    @XmlElement
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false)
    @XmlElement
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments;

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