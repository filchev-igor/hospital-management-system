package lt.dev.hospitalmanagementsystem.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String specialty;

    // Getters and Setters
}
