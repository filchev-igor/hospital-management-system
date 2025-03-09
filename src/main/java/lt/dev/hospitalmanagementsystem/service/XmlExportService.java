package lt.dev.hospitalmanagementsystem.service;

import jakarta.xml.bind.JAXBException;
import lt.dev.hospitalmanagementsystem.model.Appointment;

import java.util.List;

/**
 * Service interface for exporting appointment data to XML files.
 */
public interface XmlExportService {
    /**
     * Exports a list of appointments to an XML file.
     *
     * @param appointments the list of appointments to export
     * @return the file path of the generated XML file
     * @throws JAXBException if XML marshalling fails
     */
    String exportAppointmentsToXml(List<Appointment> appointments) throws JAXBException;
}