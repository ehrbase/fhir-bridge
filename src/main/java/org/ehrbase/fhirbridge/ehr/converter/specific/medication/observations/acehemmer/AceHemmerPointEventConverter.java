package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.acehemmer;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AceHemmerPointEventConverter extends GeccoMedicationPointEventConverter<AceHemmerBeliebigesEreignisPointEvent> {
    @Override
    protected AceHemmerBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AceHemmerBeliebigesEreignisPointEvent aceHemmerBeliebigesEreignisPointEvent = new AceHemmerBeliebigesEreignisPointEvent();
        return aceHemmerBeliebigesEreignisPointEvent;
    }
}
