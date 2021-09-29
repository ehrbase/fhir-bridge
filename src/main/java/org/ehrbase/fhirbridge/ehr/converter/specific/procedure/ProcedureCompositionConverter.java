package org.ehrbase.fhirbridge.ehr.converter.specific.procedure;

import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.lang.NonNull;

public class ProcedureCompositionConverter extends ProcedureToCompositionConverter<ProzedurComposition> {

    @Override
    public ProzedurComposition convertInternal(@NonNull Procedure resource) {
        ProzedurComposition composition = new ProzedurComposition();
        composition.setProzedur(new ProcedureActionConverter().convert(resource));
        return composition;
    }
}
