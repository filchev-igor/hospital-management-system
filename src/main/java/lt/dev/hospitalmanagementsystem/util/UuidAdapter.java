package lt.dev.hospitalmanagementsystem.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.util.UUID;

public class UuidAdapter extends XmlAdapter<String, UUID> {
    @Override
    public UUID unmarshal(String v) throws Exception {
        return UUID.fromString(v);
    }

    @Override
    public String marshal(UUID v) throws Exception {
        return v.toString();
    }
}