package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientRepository patientRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void getAllPatients_shouldReturnAllPatients() throws Exception {
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setName("John Doe");
        patient.setDob(LocalDate.now());
        patient.setCreationDate(LocalDateTime.now());
        patient.setUpdateDate(LocalDateTime.now());

        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findAll()).thenReturn(patients);

        mockMvc.perform(get("/api/patients")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addPatient_shouldSaveAndReturnPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setName("John Doe");
        patient.setDob(LocalDate.now());
        patient.setCreationDate(LocalDateTime.now());
        patient.setUpdateDate(LocalDateTime.now());

        when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + patient.getId() + "\",\"name\":\"John Doe\",\"dob\":\"" + LocalDate.now() + "\"}"))
                .andExpect(status().isOk());
    }
}