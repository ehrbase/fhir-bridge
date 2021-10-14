package org.ehrbase.fhirbridge.ehr.converter.specific.medication.covid19therapie;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class Covid19TherapieBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<Covid19TherapieBeliebigesEreignisPointEvent> {

    @Override
    protected Covid19TherapieBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        Covid19TherapieBeliebigesEreignisPointEvent covid19TherapieBeliebigesEreignisPointEvent = new Covid19TherapieBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(covid19TherapieBeliebigesEreignisPointEvent::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(covid19TherapieBeliebigesEreignisPointEvent::setGrund);
        return covid19TherapieBeliebigesEreignisPointEvent;
    }
}