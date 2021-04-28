package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.antikoagulanzien;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AntikoagulanzienPointEventConverter extends GeccoMedicationPointEventConverter<AntikoagulanzienBeliebigesEreignisPointEvent> {
    @Override
    protected AntikoagulanzienBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AntikoagulanzienBeliebigesEreignisPointEvent antikoagulanzienBeliebigesEreignisPointEvent = new AntikoagulanzienBeliebigesEreignisPointEvent();
        return antikoagulanzienBeliebigesEreignisPointEvent;
    }
}
