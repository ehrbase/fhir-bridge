package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.BefundDerBlutgasanalyseKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BloodGasPanelCompositionConverter extends ObservationToCompositionConverter<BefundDerBlutgasanalyseComposition> {

    @Override
    public BefundDerBlutgasanalyseComposition convertInternal(@NonNull Observation resource) {
        BloodGasPanel bloodGasPanelBundle = new BloodGasPanel(resource);
        Observation bloodGasPanel = bloodGasPanelBundle.getBloodGasPanel();
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();
        befundDerBlutgasanalyseComposition.setKategorie(mapKategorie(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setLaborergebnis(new LaborergebnisBefundObservationConverter().convert(resource));
        return befundDerBlutgasanalyseComposition;
    }


    private List<BefundDerBlutgasanalyseKategorieElement> mapKategorie(Observation fhirObservation) {
        Optional<String> categoryCode;
        List<BefundDerBlutgasanalyseKategorieElement> befundDerBlutgasanalyseKategorieElements = new ArrayList<>();
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (categoryCode.isPresent()) {
                BefundDerBlutgasanalyseKategorieElement befundDerBlutgasanalyseKategorieElement = new BefundDerBlutgasanalyseKategorieElement();
                befundDerBlutgasanalyseKategorieElement.setValue(categoryCode.get());
                befundDerBlutgasanalyseKategorieElements.add(befundDerBlutgasanalyseKategorieElement);
            }
        }
        if (befundDerBlutgasanalyseKategorieElements.size() != 0) {
            return befundDerBlutgasanalyseKategorieElements;
        } else {
            throw new IllegalArgumentException("Category code is not defined in blood gas panel, therefore the bundle is incomplete. Please add category observation category to the panel");
        }
    }

    private Optional<String> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry.getCode());
            }
        }
        return Optional.empty();
    }

    private boolean isObservationCategory(Coding categories) {
        return categories.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category");
    }
}