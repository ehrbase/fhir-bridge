package org.ehrbase.fhirbridge.ehr.converter.specific.ucc_sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;

public class UCCSensordatenCompositionConverter extends ObservationToCompositionConverter<BefundDerBlutgasanalyseComposition> {
    @Override
    protected BefundDerBlutgasanalyseComposition convertInternal(Observation resource) {
        return null;
    }
}
