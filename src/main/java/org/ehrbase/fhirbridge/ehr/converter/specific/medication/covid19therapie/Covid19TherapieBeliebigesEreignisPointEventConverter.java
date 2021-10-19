package org.ehrbase.fhirbridge.ehr.converter.specific.medication.covid19therapie;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class Covid19TherapieBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<Covid19TherapieBeliebigesEreignisPointEvent> {

    @Override
    protected Covid19TherapieBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        Covid19TherapieBeliebigesEreignisPointEvent pointEvent = new Covid19TherapieBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                pointEvent.setArzneimittelName(CodingToDvCodedTextConverter.getInstance().convert(coding));
            }
        }
        getGrundDefiningCode(resource).ifPresent(pointEvent::setGrund);
        return pointEvent;
    }
}