package org.ehrbase.fhirbridge.ehr.converter.specific.medication.covid19therapie;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.ArrayList;
import java.util.List;

public class Covid19TherapieObservationConverter extends GeccoMedikationObservationConverter<Covid19TherapieObservation> {

    @Override
    protected Covid19TherapieObservation convertInternal(MedicationStatement resource) {
        Covid19TherapieObservation covid19TherapieObservation = new Covid19TherapieObservation();
        List<Covid19TherapieBeliebigesEreignisChoice> ereignisList = new ArrayList<>();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                ereignisList.add(new Covid19TherapieBeliebigesEreignisPointEventConverter().convert(resource));
            }
        }
        covid19TherapieObservation.setBeliebigesEreignis(ereignisList);
        return covid19TherapieObservation;
    }

}
