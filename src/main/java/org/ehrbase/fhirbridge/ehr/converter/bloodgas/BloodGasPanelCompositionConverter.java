package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodGasPanelCompositionConverter extends AbstractCompositionConverter<Observation, BefundDerBlutgasanalyseComposition> {

    @Override
    public BefundDerBlutgasanalyseComposition convert(@NonNull Observation observation) {
        BloodGasPanel bloodGasPanel = new BloodGasPanel(observation);
        return new BlutgasAnalyseConverter()
                .convert(bloodGasPanel, buildFeederAudit(observation));
    }
}
