package org.ehrbase.fhirbridge.fhir.support;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "fb_patient_id")
public class PatientId {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public String getUuidAsString() {
        if (uuid == null) {
            return null;
        }
        return uuid.toString();
    }
}
