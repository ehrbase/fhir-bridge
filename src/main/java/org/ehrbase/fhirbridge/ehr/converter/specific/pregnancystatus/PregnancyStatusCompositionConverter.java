package org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class PregnancyStatusCompositionConverter extends ObservationToCompositionConverter<SchwangerschaftsstatusComposition> {

    @Override
    public SchwangerschaftsstatusComposition convertInternal(@NonNull Observation resource) {
        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();
        composition.setSchwangerschaftsstatus(new SchwangerschaftsstatusObservationConverter().convert(resource));
        mapStatus(resource, composition);
        mapKategorie(resource, composition).ifPresent(composition::setKategorieValue);
        return composition;
    }

    private void mapStatus(Observation resource, SchwangerschaftsstatusComposition composition) {
        switch (resource.getStatusElement().getCode()) {
            case "registered":
                composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
                break;
            case "final":
                composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
                break;
            case "amended":
            case "corrected":
                composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
                break;
            case "preliminary":
                composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
                break;
            case"cancelled":
            case"entered-in-error":
                composition.setStatusNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE); //TODO not sure if this is good
                break;
            case"unknown":
                composition.setStatusNullFlavourDefiningCode(NullFlavour.UNKNOWN);
                break;
            default:
                throw new ConversionException("Invalid Code " + resource.getStatusElement().getCode() + "" +
                        " for mapping of 'status'");
        }
    }

    private Optional<String> mapKategorie(Observation resource, SchwangerschaftsstatusComposition composition) {
        if (resource.hasCategory()) {
            return resource.getCategory()
                    .stream()
                    .filter(CodeableConcept::hasCoding)
                    .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                    .map(Coding::getCode)
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }
}
