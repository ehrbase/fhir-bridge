package org.ehrbase.fhirbridge.ehr.converter.specific.medication.antikoagulanzien;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AntikoagulanzienBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<AntikoagulanzienBeliebigesEreignisPointEvent> {

    @Override
    protected AntikoagulanzienBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AntikoagulanzienBeliebigesEreignisPointEvent antikoagulanzienBeliebigesEreignisPointEvent = new AntikoagulanzienBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                DvCodedTextParser.getInstance()
                        .parseFHIRCoding(coding)
                        .ifPresent(antikoagulanzienBeliebigesEreignisPointEvent::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(antikoagulanzienBeliebigesEreignisPointEvent::setGrund);
        return antikoagulanzienBeliebigesEreignisPointEvent;
    }
}
