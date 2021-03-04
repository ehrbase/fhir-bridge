package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.hl7.fhir.r4.model.Observation;

public class AntiBodyPanelCompositionConverter implements CompositionConverter<GECCOSerologischerBefundComposition, Observation> {
    @Override
    public Observation fromComposition(GECCOSerologischerBefundComposition composition) throws CompositionConversionException {
        return null;
    }

    @Override
    public GECCOSerologischerBefundComposition toComposition(Observation observation) throws CompositionConversionException {
        GECCOSerologischerBefundComposition geccoSerologischerBefundComposition = new GECCOSerologischerBefundComposition();
        AntiBodyPanel antiBodyPanel = new AntiBodyPanel(observation);


        return geccoSerologischerBefundComposition;
    }
}
