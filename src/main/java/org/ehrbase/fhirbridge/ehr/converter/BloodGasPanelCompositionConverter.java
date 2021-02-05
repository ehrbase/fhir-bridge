package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;

public class BloodGasPanelCompositionConverter implements CompositionConverter<BefundDerBlutgasanalyseComposition, Observation> {

    @Override
    public Observation fromComposition(BefundDerBlutgasanalyseComposition composition) throws CompositionConversionException {
        return new Observation();
    }

    @Override
    public BefundDerBlutgasanalyseComposition toComposition(Observation observation) throws CompositionConversionException {
        // TODO: Thank you so much @SevKohler :)
        return new BefundDerBlutgasanalyseComposition();
    }
}
