package org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SarsCov2KnownExposureCompositionConverter extends ObservationToCompositionConverter<SARSCoV2ExpositionComposition> {

    @Override
    public SARSCoV2ExpositionComposition  convertInternal(@NonNull Observation resource) {
        SARSCoV2ExpositionComposition composition = new SARSCoV2ExpositionComposition();

        mapStatus(composition, resource);

        composition.setSarsCov2Exposition(new SarsCov2ExpositionEvaluationConverter().convert(resource));
        return composition;
    }

    private void mapStatus(SARSCoV2ExpositionComposition composition, Observation obs) {
        String status = obs.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + obs.getStatus().toString() + " is not valid for known exposure.");
        }
    }
}
