package org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab;

import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.KDSLaborberichtComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.List;

public class KDSObservationLabCompositionConverter extends ObservationToCompositionConverter<KDSLaborberichtComposition> {

    @Override
    public KDSLaborberichtComposition convertInternal(@NonNull Observation resource) {
        KDSLaborberichtComposition composition = new KDSLaborberichtComposition();
        composition.setLaborbefund(new LaborergebnisObservationConverter(List.of(resource)).convert(resource));
        composition.setStatusValue(getRegisterEintrag(resource));
        return composition;
    }

    private String getRegisterEintrag(Observation resource) {
        switch (resource.getStatus()) {
            case FINAL:
                return "final";
            case CORRECTED:
                return "geänedert";
            case PRELIMINARY:
                return "registriert";
            case REGISTERED:
                return "vorläufig";
            default:
                throw new InvalidStatusCodeException(resource.getStatus().toString());
        }
    }
}
