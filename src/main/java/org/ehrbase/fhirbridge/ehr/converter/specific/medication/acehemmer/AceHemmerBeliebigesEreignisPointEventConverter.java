package org.ehrbase.fhirbridge.ehr.converter.specific.medication.acehemmer;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;


public class AceHemmerBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<AceHemmerBeliebigesEreignisPointEvent> {
    @Override
    protected AceHemmerBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AceHemmerBeliebigesEreignisPointEvent event = new AceHemmerBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                event.setArzneimittelName(CodingToDvCodedTextConverter.getInstance().convert(coding));
            }
        }
        getGrundDefiningCode(resource).ifPresent(event::setGrund);
        return event;
    }
}
