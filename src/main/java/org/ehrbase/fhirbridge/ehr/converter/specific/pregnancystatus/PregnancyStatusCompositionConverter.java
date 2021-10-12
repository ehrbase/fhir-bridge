package org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PregnancyStatusCompositionConverter extends ObservationToCompositionConverter<SchwangerschaftsstatusComposition> {

    @Override
    public SchwangerschaftsstatusComposition convertInternal(@NonNull Observation resource) {
        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();
        composition.setSchwangerschaftsstatus(new SchwangerschaftsstatusObservationConverter().convert(resource));
        convertKategorie(resource, composition).ifPresent(composition::setKategorie);
        return composition;
    }

    private Optional<List<SchwangerschaftsstatusKategorieElement>> convertKategorie(Observation resource, SchwangerschaftsstatusComposition composition) {
        List<SchwangerschaftsstatusKategorieElement> schwangerschaftsstatusKategorieElements = mapKategorie(resource);
        if(schwangerschaftsstatusKategorieElements.isEmpty()){
            return Optional.of(schwangerschaftsstatusKategorieElements);
        }else{
            return Optional.empty();
        }
    }

    private List<SchwangerschaftsstatusKategorieElement> mapKategorie(Observation resource) {
        List<SchwangerschaftsstatusKategorieElement> schwangerschaftsstatusKategorieElements = new ArrayList<>();
        if (resource.hasCategory()) {
            return convertSchwangerschaftsstatusKategorie(resource, schwangerschaftsstatusKategorieElements);
        } else {
            return new ArrayList<>();
        }
    }

    private List<SchwangerschaftsstatusKategorieElement> convertSchwangerschaftsstatusKategorie(Observation resource, List<SchwangerschaftsstatusKategorieElement> schwangerschaftsstatusKategorieElements) {
        resource.getCategory()
                .stream()
                .filter(CodeableConcept::hasCoding)
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .map(Coding::getCode)
                .forEach(code -> {
                    SchwangerschaftsstatusKategorieElement schwangerschaftsstatusKategorieElement = new SchwangerschaftsstatusKategorieElement();
                    schwangerschaftsstatusKategorieElement.setValue(code);
                    schwangerschaftsstatusKategorieElements.add(schwangerschaftsstatusKategorieElement);
                });
        return schwangerschaftsstatusKategorieElements;
    }
}
