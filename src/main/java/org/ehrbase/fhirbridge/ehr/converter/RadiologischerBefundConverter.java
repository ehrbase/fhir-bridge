package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;

public class RadiologischerBefundConverter implements CompositionConverter<GECCORadiologischerBefundComposition, DiagnosticReport> {

    @Override
    public DiagnosticReport fromComposition(GECCORadiologischerBefundComposition composition) throws CompositionConversionException {
        return new DiagnosticReport();
    }

    @Override
    public GECCORadiologischerBefundComposition toComposition(DiagnosticReport object) throws CompositionConversionException {
        return new GECCORadiologischerBefundComposition();
    }


}
