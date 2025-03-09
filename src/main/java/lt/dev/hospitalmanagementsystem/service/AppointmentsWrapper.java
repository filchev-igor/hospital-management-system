package lt.dev.hospitalmanagementsystem.service;

import lombok.Setter;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Wrapper class for a list of appointments to facilitate XML marshalling.
 */
@Setter
@XmlRootElement(name = "appointments")
public class AppointmentsWrapper {

    private List<Appointment> appointments;

    @XmlElement(name = "appointment")
    public List<Appointment> getAppointments() {
        return appointments;
    }

}