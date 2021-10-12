package org.ehrbase.fhirbridge.ehr.converter.specific.medication.acehemmer;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;


public class AceHemmerBeliebigesEreignisPointEventConverter extends GeccoMedikationPointEventConverter<AceHemmerBeliebigesEreignisPointEvent> {
    @Override
    protected AceHemmerBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        AceHemmerBeliebigesEreignisPointEvent aceHemmerBeliebigesEreignisPointEvent = new AceHemmerBeliebigesEreignisPointEvent();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(aceHemmerBeliebigesEreignisPointEvent::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(aceHemmerBeliebigesEreignisPointEvent::setGrund);
        return aceHemmerBeliebigesEreignisPointEvent;
    }
}
