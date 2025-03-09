package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorRepository doctorRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    void getAllDoctors_shouldReturnAllDoctors() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(UUID.randomUUID());
        doctor.setName("Dr. John");
        doctor.setSpecialty("Cardiology");
        doctor.setCreationDate(LocalDateTime.now());
        doctor.setUpdateDate(LocalDateTime.now());

        List<Doctor> doctors = Arrays.asList(doctor);
        when(doctorRepository.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/api/doctors")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addDoctor_shouldSaveAndReturnDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(UUID.randomUUID());
        doctor.setName("Dr. John");
        doctor.setSpecialty("Cardiology");
        doctor.setCreationDate(LocalDateTime.now());
        doctor.setUpdateDate(LocalDateTime.now());

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        mockMvc.perform(post("/api/doctors")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + doctor.getId() + "\",\"name\":\"Dr. John\",\"specialty\":\"Cardiology\"}"))
                .andExpect(status().isOk());
    }
}