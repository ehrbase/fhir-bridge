package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SmokingStatusCompositionConverter extends ObservationToCompositionConverter<RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convertInternal(@NonNull Observation resource) {
        RaucherstatusComposition composition = new RaucherstatusComposition();
        composition.setRaucherstatus(new RaucherstatusEvaluationConverter().convert(resource));
        return composition;
    }
}
