package org.ehrbase.fhirbridge.ehr.converter.specific.medication.immunoglobuline;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class ImmunglobulineBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<ImmunglobulineBeliebigesEreignisPointEvent> {

    @Override
    protected ImmunglobulineBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        ImmunglobulineBeliebigesEreignisPointEvent pointEvent = new ImmunglobulineBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                pointEvent.setArzneimittelName(CodingToDvCodedTextConverter.getInstance().convert(coding));
            }
        }
        getGrundDefiningCode(resource).ifPresent(pointEvent::setGrund);
        return pointEvent;
    }
}