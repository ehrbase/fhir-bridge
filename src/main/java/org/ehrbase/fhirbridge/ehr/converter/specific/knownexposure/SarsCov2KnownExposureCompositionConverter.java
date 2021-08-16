package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SarsCov2KnownExposureCompositionConverter extends ObservationToCompositionConverter<SARSCoV2ExpositionComposition> {

    @Override
    public SARSCoV2ExpositionComposition convertInternal(@NonNull Observation resource) {
        SARSCoV2ExpositionComposition composition = new SARSCoV2ExpositionComposition();
        composition.setSarsCov2Exposition(new SarsCov2ExpositionEvaluationConverter().convert(resource));
        return composition;
    }

}
