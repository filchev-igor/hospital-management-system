package lt.dev.hospitalmanagementsystem.controller;

import lt.dev.hospitalmanagementsystem.dto.AppointmentDTO;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import lt.dev.hospitalmanagementsystem.model.Doctor;
import lt.dev.hospitalmanagementsystem.model.Patient;
import lt.dev.hospitalmanagementsystem.repository.AppointmentRepository;
import lt.dev.hospitalmanagementsystem.service.XmlExportService;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private XmlExportService xmlExportService;

    private MockMvc mockMvc;

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();

        appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setCreationDate(LocalDateTime.now());
        appointment.setUpdateDate(LocalDateTime.now());
        appointment.setPatient(new Patient());
        appointment.getPatient().setId(UUID.randomUUID());
        appointment.setDoctor(new Doctor());
        appointment.getDoctor().setId(UUID.randomUUID());
    }

    @Test
    void exportAppointmentsToXml_shouldReturnFilePath() throws Exception {
        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointments);
        when(xmlExportService.exportAppointmentsToXml(anyList())).thenReturn("exported_xml/appointments_20250309_123456.xml");

        mockMvc.perform(get("/api/appointments/export")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("exported_xml/appointments_20250309_123456.xml"));
    }

    @Test
    void exportAppointmentsToXml_withDoctorId_shouldReturnFilePath() throws Exception {
        UUID doctorId = UUID.randomUUID();
        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findByDoctorId(doctorId)).thenReturn(appointments);
        when(xmlExportService.exportAppointmentsToXml(anyList())).thenReturn("exported_xml/appointments_20250309_123456.xml");

        mockMvc.perform(get("/api/appointments/export")
                        .param("doctorId", doctorId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("exported_xml/appointments_20250309_123456.xml"));
    }

    @Test
    void getAllAppointments_shouldReturnAllAppointments() throws Exception {
        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointments);

        mockMvc.perform(get("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addAppointment_shouldSaveAndReturnAppointment() throws Exception {
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        String jsonContent = "{\"appointmentDate\":\"" + LocalDateTime.now() + "\","
                + "\"patient\":{\"id\":\"" + appointment.getPatient().getId() + "\"},"
                + "\"doctor\":{\"id\":\"" + appointment.getDoctor().getId() + "\"}}";

        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}