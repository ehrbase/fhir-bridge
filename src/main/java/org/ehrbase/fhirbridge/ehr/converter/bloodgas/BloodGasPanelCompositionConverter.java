package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import ca.uhn.fhir.context.FhirContext;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

public class BloodGasPanelCompositionConverter implements CompositionConverter<BefundDerBlutgasanalyseComposition, Observation> {

    @Override
    public Observation fromComposition(BefundDerBlutgasanalyseComposition composition) {

        return new Observation();
    }

    @Override
    public BefundDerBlutgasanalyseComposition toComposition(Observation obseravtion) throws CompositionConversionException {
        System.out.println(obseravtion.getMeta().getProfile());
        System.out.println(obseravtion.getContained());
    //    BloodGasPanel bloodGasPanel = new BloodGasPanel(obseravtion);
        BlutgasAnalyseConverter blutgasAnalyseMapper = new BlutgasAnalyseConverter();
       // BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = blutgasAnalyseMapper.convert(bloodGasPanel);
        return new BefundDerBlutgasanalyseComposition();
    }

}
