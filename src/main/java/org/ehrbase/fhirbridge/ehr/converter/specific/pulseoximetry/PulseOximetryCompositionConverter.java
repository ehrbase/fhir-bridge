package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class PulseOximetryCompositionConverter extends ObservationToCompositionConverter<PulsoxymetrieComposition> {

    @Override
    public PulsoxymetrieComposition convertInternal(@NonNull Observation resource) {
        new PulseOximetryCodeChecker().checkIsPulsOximetry(resource);
        PulsoxymetrieComposition composition = new PulsoxymetrieComposition();
        mapKategorie(resource).ifPresent(composition::setKategorieValue);
        composition.setPulsoxymetrie(new PulsoxymetrieObservationConverter().convert(resource));
        return composition;
    }

    private Optional<String> mapKategorie(Observation observation) {
        return observation.getCategory().stream()
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .filter(Coding::hasCode)
                .map(Coding::getCode)
                .findFirst();
    }

}
