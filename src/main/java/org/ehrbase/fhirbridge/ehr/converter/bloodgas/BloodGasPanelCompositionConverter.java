package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodGasPanelCompositionConverter extends CompositionConverter<Observation, BefundDerBlutgasanalyseComposition> {

    @Override
    public BefundDerBlutgasanalyseComposition convertInternal(@NonNull Observation resource) {
        BloodGasPanel bloodGasPanel = new BloodGasPanel(resource);
        return new BlutgasAnalyseConverter()
                .convert(bloodGasPanel, buildFeederAudit(resource));
    }
}
