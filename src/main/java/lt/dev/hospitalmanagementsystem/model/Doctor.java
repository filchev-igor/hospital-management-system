package lt.dev.hospitalmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lt.dev.hospitalmanagementsystem.util.LocalDateTimeAdapter;
import lt.dev.hospitalmanagementsystem.util.UuidAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a doctor in the hospital management system.
 */
@Getter
@Setter
@Entity
@Table(name = "doctors")
@XmlRootElement(name = "doctor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    @XmlJavaTypeAdapter(UuidAdapter.class)
    private UUID id;

    @Column(nullable = false)
    @XmlElement
    private String name;

    @XmlElement
    private String specialty;

    @Column(name = "creation_date", nullable = false)
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false)
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    @XmlTransient // Exclude from XML to avoid cyclic references
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