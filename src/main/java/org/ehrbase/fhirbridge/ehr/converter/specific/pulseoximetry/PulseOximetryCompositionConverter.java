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
        mapStatus(composition, resource);
        mapKategorie(resource).ifPresent(composition::setKategorieValue);
        composition.setPulsoxymetrie(new PulsoxymetrieObservationConverter().convert(resource));
        return composition;
    }

    private void mapStatus(PulsoxymetrieComposition composition, Observation resource) {
        String status = resource.getStatusElement().getCode();
        switch (status) {
            case "registered":
                composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
                break;
            case "final":
                composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
                break;
            case "amended":
                composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
                break;
            case "preliminary":
                composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
                break;
            default:
                composition.setStatusDefiningCode(StatusDefiningCode.FINAL); //TODO wait until status modeling is done to cover all status codes
                //          throw new IllegalStateException("Invalid Code " + status + "" +
        //                " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    private Optional<String> mapKategorie(Observation observation) {
        return observation.getCategory().stream()
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .filter(Coding::hasCode)
                .map(Coding::getCode)
                .findFirst();
    }

}
