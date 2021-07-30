package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class RaucherstatusCompositionConverter extends ObservationToCompositionConverter<RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convertInternal(@NonNull Observation resource) {
        RaucherstatusComposition composition = new RaucherstatusComposition();
        mapStatus(resource, composition);
        mapCategory(resource).ifPresent(composition::setKategorieValue);
        mapEffectiveTimeDateAbsent(resource, composition).ifPresent(composition::setStatusNullFlavourDefiningCode); //introduce reflection to set nullflavour
        composition.setRaucherstatus(new RaucherstatusEvaluationConverter().convert(resource));
        return composition;
    }

    private Optional<NullFlavour> mapEffectiveTimeDateAbsent(Observation resource, RaucherstatusComposition composition) {
        if (resource.hasEffectiveDateTimeType() && resource.getEffectiveDateTimeType().hasExtension()) {
            return convertExtension(resource, composition);
        }
        return Optional.empty();
    }

    private Optional<NullFlavour> convertExtension(Observation resource, RaucherstatusComposition composition) {
        for (Extension extension : resource.getEffectiveDateTimeType().getExtension()) {
            if (extension.hasUrl() && extension.getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
                composition.setStartTimeValue(null);
                return Optional.of(NullFlavour.UNKNOWN);
            }
        }
        return Optional.empty();
    }

    private Optional<String> mapCategory(Observation resource) {
        if (resource.hasCategory()) {
            return resource.getCategory().stream()
                    .filter(CodeableConcept::hasCoding)
                    .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                    .filter(Coding::hasCode)
                    .map(Coding::getCode)
                    .findFirst();
        }
        return Optional.empty();
    }

    private void mapStatus(Observation resource, RaucherstatusComposition composition) {
        switch (resource.getStatusElement().getCode()) {
            case "registered":
                composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
                break;
            case "preliminary":
                composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
                break;
            case "final":
                composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
                break;
            case "amended":
                composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
                break;
            default:
                composition.setStatusNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

}
