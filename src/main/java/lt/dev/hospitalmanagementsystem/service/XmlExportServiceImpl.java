package lt.dev.hospitalmanagementsystem.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.dev.hospitalmanagementsystem.model.Appointment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Implementation of XmlExportService for exporting appointments to XML.
 */
@Service
public class XmlExportServiceImpl implements XmlExportService {

    private static final String EXPORT_DIR = "exported_xml/";

    /**
     * Exports a list of appointments to an XML file.
     *
     * @param appointments the list of appointments to export
     * @return the file path of the generated XML file
     * @throws JAXBException if XML marshalling fails
     */
    @Override
    public String exportAppointmentsToXml(List<Appointment> appointments) throws JAXBException {
        // Create export directory if it doesn't exist
        File exportDir = new File(EXPORT_DIR);
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        // Generate a unique file name based on timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = EXPORT_DIR + "appointments_" + timestamp + ".xml";

        // Create a wrapper for the list to make the XML structure cleaner
        AppointmentsWrapper wrapper = new AppointmentsWrapper();
        wrapper.setAppointments(appointments);

        // Export to XML using JAXB
        JAXBContext context = JAXBContext.newInstance(AppointmentsWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapper, new File(filePath));

        return filePath;
    }
}