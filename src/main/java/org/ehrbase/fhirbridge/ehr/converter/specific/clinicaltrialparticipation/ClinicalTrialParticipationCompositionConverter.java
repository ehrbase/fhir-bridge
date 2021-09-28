package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.GECCOStudienteilnahmeComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class ClinicalTrialParticipationCompositionConverter extends ObservationToCompositionConverter<GECCOStudienteilnahmeComposition> {

    @Override
    protected GECCOStudienteilnahmeComposition convertInternal(@NonNull Observation resource) {
        GECCOStudienteilnahmeComposition composition = new GECCOStudienteilnahmeComposition();
        composition.setGeccoStudienteilnahme(new ClinicalTrialParticipationEvaluationConverter().convert(resource));
        return composition;
    }

}