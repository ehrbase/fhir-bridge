package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.immunoglobuline;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.MedicationStatement;

public class ImmunglobulinePointEventConverter extends GeccoMedicationPointEventConverter<ImmunglobulineBeliebigesEreignisPointEvent> {
    @Override
    protected ImmunglobulineBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        ImmunglobulineBeliebigesEreignisPointEvent immunglobulineBeliebigesEreignisPointEvent = new ImmunglobulineBeliebigesEreignisPointEvent();
        return immunglobulineBeliebigesEreignisPointEvent;
    }
}
