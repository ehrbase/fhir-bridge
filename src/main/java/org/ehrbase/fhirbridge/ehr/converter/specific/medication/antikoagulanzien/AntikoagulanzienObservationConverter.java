package org.ehrbase.fhirbridge.ehr.converter.specific.medication.antikoagulanzien;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.ArrayList;
import java.util.List;

public class AntikoagulanzienObservationConverter extends GeccoMedikationObservationConverter<AntikoagulanzienObservation> {

    @Override
    protected AntikoagulanzienObservation convertInternal(MedicationStatement resource) {
        AntikoagulanzienObservation antikoagulanzienObservation = new AntikoagulanzienObservation();
        List<AntikoagulanzienBeliebigesEreignisChoice> ereignisList = new ArrayList<>();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                ereignisList.add(new AntikoagulanzienBeliebigesEreignisPointEventConverter().convert(resource));
            }
        }
        antikoagulanzienObservation.setBeliebigesEreignis(ereignisList);
        return antikoagulanzienObservation;
    }
}
