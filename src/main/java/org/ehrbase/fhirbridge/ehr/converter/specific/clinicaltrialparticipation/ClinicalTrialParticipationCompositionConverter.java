package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.GECCOStudienteilnahmeComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class ClinicalTrialParticipationCompositionConverter extends ObservationToCompositionConverter<GECCOStudienteilnahmeComposition> {

    @Override
    protected GECCOStudienteilnahmeComposition convertInternal(@NonNull Observation resource) {
        GECCOStudienteilnahmeComposition composition = new GECCOStudienteilnahmeComposition();
        mapStatus(composition, resource);
        composition.setGeccoStudienteilnahme(new ClinicalTrialParticipationEvaluationConverter().convert(resource));
        return composition;
    }

    private void mapStatus(GECCOStudienteilnahmeComposition composition, Observation obs) {
        String status = obs.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + obs.getStatus().toString() + " is not valid for clinical trial participation.");
        }
    }
}