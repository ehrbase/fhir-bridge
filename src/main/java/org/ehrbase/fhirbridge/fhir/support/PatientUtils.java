package org.ehrbase.fhirbridge.fhir.support;

import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;

public class PatientUtils {

    private static final String IDENTIFIER_SYSTEM = "http://www.netzwerk-universitaetsmedizin.de/sid/crr-pseudonym";

    private PatientUtils() {
    }

    public static Identifier getPseudonym(Patient patient) {
        return patient.getIdentifier().stream()
                .filter(identifier -> IDENTIFIER_SYSTEM.equals(identifier.getSystem()))
                .findFirst()
                .or(() -> patient.getIdentifier().stream().findFirst())
                .orElseThrow(() -> new InvalidRequestException("Patient must have an identifier with system: " + IDENTIFIER_SYSTEM));
    }
}
