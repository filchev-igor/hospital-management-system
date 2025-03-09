package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.dto.AppointmentDTO;
import lt.dev.hospitalmanagementsystem.dto.DoctorDTO;
import lt.dev.hospitalmanagementsystem.dto.PatientDTO;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void getAllAppointments_shouldReturnAllAppointments() throws Exception {
        // Mock data
        UUID appointmentId = UUID.randomUUID();
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setCreationDate(LocalDateTime.now());
        appointment.setUpdateDate(LocalDateTime.now());
        appointment.setPatient(new Patient());
        appointment.setDoctor(new Doctor());

        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Perform GET request and verify
        mockMvc.perform(get("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppointments_withDoctorId_shouldReturnFilteredAppointments() throws Exception {
        // Mock data
        UUID doctorId = UUID.randomUUID();
        Appointment appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setCreationDate(LocalDateTime.now());
        appointment.setUpdateDate(LocalDateTime.now());
        appointment.setPatient(new Patient());
        appointment.setDoctor(new Doctor());
        appointment.getDoctor().setId(doctorId);

        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findByDoctorId(doctorId)).thenReturn(appointments);

        // Perform GET request with query parameter and verify
        mockMvc.perform(get("/api/appointments")
                        .param("doctorId", doctorId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addAppointment_shouldSaveAndReturnAppointment() throws Exception {
        // Mock data
        UUID appointmentId = UUID.randomUUID();
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setCreationDate(LocalDateTime.now());
        appointment.setUpdateDate(LocalDateTime.now());
        appointment.setPatient(new Patient());
        appointment.setDoctor(new Doctor());

        AppointmentDTO expectedDTO = new AppointmentDTO();
        expectedDTO.setId(appointmentId);
        expectedDTO.setAppointmentDate(LocalDateTime.now());
        expectedDTO.setCreationDate(LocalDateTime.now());
        expectedDTO.setUpdateDate(LocalDateTime.now());
        expectedDTO.setPatient(new PatientDTO());
        expectedDTO.setDoctor(new DoctorDTO());

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Perform POST request and verify
        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + appointmentId + "\",\"appointmentDate\":\"" + LocalDateTime.now() + "\"}"))
                .andExpect(status().isOk());
    }
}