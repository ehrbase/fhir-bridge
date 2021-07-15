package org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten;

import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationInternetKommunikationCluster;
import org.hl7.fhir.r4.model.ContactPoint;

import java.util.List;
import java.util.Optional;

public class InternetKommunikationConverter {
    public static Optional<List<EinzelheitenDerKommunikationInternetKommunikationCluster>> convert(ContactPoint telecom) {
        return null;
    }
}
