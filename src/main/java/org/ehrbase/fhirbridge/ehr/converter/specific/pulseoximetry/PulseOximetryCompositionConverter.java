package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PulseOximetryCompositionConverter extends ObservationToCompositionConverter<PulsoxymetrieComposition> {

    @Override
    public PulsoxymetrieComposition convertInternal(@NonNull Observation resource) {
        new PulseOximetryCodeChecker().checkIsPulsOximetry(resource);
        PulsoxymetrieComposition composition = new PulsoxymetrieComposition();
        mapStatus(composition, resource);
        mapKategorie(composition, resource);
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
                throw new IllegalStateException("Invalid Code " + status + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    private void mapKategorie(PulsoxymetrieComposition composition, Observation observation) {
        if (observation.getCategory().size() > 1) {
            throw new ConversionException("Fhir-Bridge does not support more then one Category Code value");
        }
        composition.setKategorieValue(observation.getCategory().get(0).getCoding()
                .get(0).getCode());
    }

}
