package org.ehrbase.fhirbridge.ehr.converter.specific.medication.acehemmer;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.ArrayList;
import java.util.List;

public class AceHemmerObservationConverter extends GeccoMedikationObservationConverter<AceHemmerObservation> {

    @Override
    protected AceHemmerObservation convertInternal(MedicationStatement resource) {
        AceHemmerObservation aceHemmerObservation = new AceHemmerObservation();
        List<AceHemmerBeliebigesEreignisChoice> ereignisList = new ArrayList<>();
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")) {
                ereignisList.add(new AceHemmerBeliebigesEreignisPointEventConverter().convert(resource));
            }
        }
        aceHemmerObservation.setBeliebigesEreignis(ereignisList);
        return aceHemmerObservation;
    }

}
