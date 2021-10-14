package org.ehrbase.fhirbridge.ehr.converter.specific.medication.immunoglobuline;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.ArrayList;
import java.util.List;

public class ImmunglobulineObservationConverter extends GeccoMedikationObservationConverter<ImmunglobulineObservation> {
    @Override
    protected ImmunglobulineObservation convertInternal(MedicationStatement resource) {
        ImmunglobulineObservation immunglobulineObservation = new ImmunglobulineObservation();
        List<ImmunglobulineBeliebigesEreignisChoice> ereignisList = new ArrayList<>();
        for (Coding coding: resource.getMedicationCodeableConcept().getCoding()){
            if(coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")){
                ereignisList.add(new ImmunglobulineBeliebigesEreignisPointEventConverter().convert(resource));
            }
        }
        immunglobulineObservation.setBeliebigesEreignis(ereignisList);
        return immunglobulineObservation;
    }

}
