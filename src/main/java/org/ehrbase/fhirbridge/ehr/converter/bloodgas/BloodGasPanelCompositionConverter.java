package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;

public class BloodGasPanelCompositionConverter implements CompositionConverter<BefundDerBlutgasanalyseComposition, Observation> {

    @Override
    public Observation fromComposition(BefundDerBlutgasanalyseComposition composition) {
        return new Observation(); //TODO
    }

    @Override
    public BefundDerBlutgasanalyseComposition toComposition(Observation observation) throws CompositionConversionException {
        BloodGasPanel bloodGasPanel = new BloodGasPanel(observation);
        BlutgasAnalyseConverter blutgasAnalyseMapper = new BlutgasAnalyseConverter();
        return blutgasAnalyseMapper.convert(bloodGasPanel);
    }

}
