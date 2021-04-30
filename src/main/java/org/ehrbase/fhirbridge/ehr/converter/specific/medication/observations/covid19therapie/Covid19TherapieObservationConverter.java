package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.covid19therapie;

import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.List;

public class Covid19TherapieObservationConverter extends GeccoMedikationObservationConverter<Covid19TherapieObservation> {

    @Override
    protected Covid19TherapieObservation convertInternal(MedicationStatement resource) {
        Covid19TherapieObservation covid19TherapieObservation = new Covid19TherapieObservation();
        covid19TherapieObservation.setBeliebigesEreignis(List.of(new Covid19TherapiePointEventConverter().convert(resource)));
        return covid19TherapieObservation;
    }
}
