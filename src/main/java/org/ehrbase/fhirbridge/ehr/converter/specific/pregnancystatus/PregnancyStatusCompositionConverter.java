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
        mapKategorie(resource, composition).ifPresent(composition::setKategorieValue);
        return composition;
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
