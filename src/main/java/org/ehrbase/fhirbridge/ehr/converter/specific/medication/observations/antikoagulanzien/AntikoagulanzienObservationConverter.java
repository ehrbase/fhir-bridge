package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.antikoagulanzien;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.acehemmer.AceHemmerPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.List;

public class AntikoagulanzienObservationConverter extends GeccoMedicationObservationConverter<AntikoagulanzienObservation> {

    @Override
    protected AntikoagulanzienObservation convertInternal(MedicationStatement resource) {
        AntikoagulanzienObservation antikoagulanzienObservation = new AntikoagulanzienObservation();
        antikoagulanzienObservation.setBeliebigesEreignis(List.of(new AntikoagulanzienPointEventConverter().convert(resource)));

        return antikoagulanzienObservation;
    }
}
