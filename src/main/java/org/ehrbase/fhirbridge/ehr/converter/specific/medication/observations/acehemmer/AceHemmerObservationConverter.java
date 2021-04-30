package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.acehemmer;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.List;

public class AceHemmerObservationConverter extends GeccoMedikationObservationConverter<AceHemmerObservation> {

    @Override
    protected AceHemmerObservation convertInternal(MedicationStatement resource) {
        AceHemmerObservation aceHemmerObservation = new AceHemmerObservation();
        aceHemmerObservation.setBeliebigesEreignis(List.of(new AceHemmerPointEventConverter().convert(resource)));
        return aceHemmerObservation;
    }
}
