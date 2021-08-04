package org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.AtemfrequenzComposition;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class RespiratoryRateCompositionConverter extends ObservationToCompositionConverter<AtemfrequenzComposition> {

    @Override
    public AtemfrequenzComposition convertInternal(@NonNull Observation resource) {
        AtemfrequenzComposition composition = new AtemfrequenzComposition();
        mapStatus(composition, resource);
        mapKategorie(resource).ifPresent(composition::setKategorieValue);
        composition.setAtemfrequenz(new AtemfrequenzObservationConverter().convert(resource));
        return composition;
    }

    private Optional<String> mapKategorie( Observation resource) {
        return resource.getCategory().get(0).getCoding().stream()
                .map(Coding::getCode)
                .findFirst();
        }

    private void mapStatus(AtemfrequenzComposition composition, Observation resource) {
        switch(resource.getStatusElement().getCode()){
            case "final":
                composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
                break;
            case "amended":
                composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
                break;
            case "registered":
                composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
                break;
            case "preliminary":
                composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
                break;
            default:
                throw new ConversionException("The status " + resource.getStatus().toString() + " is not valid for radiology report. Please enter either final, amended, registered or preliminary");
        }
    }
}
