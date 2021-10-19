package org.ehrbase.fhirbridge.ehr.converter.specific.medication.antikoagulanzien;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AntikoagulanzienBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<AntikoagulanzienBeliebigesEreignisPointEvent> {

    @Override
    protected AntikoagulanzienBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AntikoagulanzienBeliebigesEreignisPointEvent pointEvent = new AntikoagulanzienBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                pointEvent.setArzneimittelName(CodingToDvCodedTextConverter.getInstance().convert(coding));
            }
        }
        getGrundDefiningCode(resource).ifPresent(pointEvent::setGrund);
        return pointEvent;
    }
}
