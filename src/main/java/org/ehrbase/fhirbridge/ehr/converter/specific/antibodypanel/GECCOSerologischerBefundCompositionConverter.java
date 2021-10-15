package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieLoincElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.KategorieLoincDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;
import java.util.Optional;

public class GECCOSerologischerBefundCompositionConverter extends ObservationToCompositionConverter<GECCOSerologischerBefundComposition> {

    @Override
    protected GECCOSerologischerBefundComposition convertInternal(Observation resource) {
        GECCOSerologischerBefundComposition geccoSerologischerBefundComposition = new GECCOSerologischerBefundComposition();
        mapKategorie(resource, geccoSerologischerBefundComposition);
        geccoSerologischerBefundComposition.setBefund(new LabratoryTestResultConverter().convert(resource));
        return geccoSerologischerBefundComposition;
    }

    private void mapKategorie(Observation fhirObservation, GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        Optional<Coding> categoryCode;
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (isKategorieDefiningCode(categoryCode)) {
                mapKategorieDefiningCode(geccoSerologischerBefundComposition);
            } else if (isKategorieLoincCode(categoryCode)) {
                mapKategorieLoincCode(geccoSerologischerBefundComposition);
            } else {
                throw new IllegalArgumentException("Category code is not defined in anti body panel, therefore the bundle is incomplete. Please add category observation category to the panel");
            }
        }
    }

    private Optional<Coding> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry);
            }
        }
        return Optional.empty();
    }

    private boolean isKategorieLoincCode(Optional<Coding> categoryCode) {
        return categoryCode.isPresent() && isLabratory(categoryCode.get().getCode());
    }

    private boolean isKategorieDefiningCode(Optional<Coding> categoryCode) {
        return categoryCode.isPresent() && isLoincCode(categoryCode.get());
    }

    private void mapKategorieLoincCode(GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        GeccoSerologischerBefundKategorieLoincElement loincElement = new GeccoSerologischerBefundKategorieLoincElement();
        loincElement.setValue(KategorieLoincDefiningCode.LABORATORY_STUDIES_SET);
        geccoSerologischerBefundComposition.setKategorieLoinc(List.of(loincElement));
    }

    private void mapKategorieDefiningCode(GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        GeccoSerologischerBefundKategorieElement geccoSerologischerBefundKategorieElement = new GeccoSerologischerBefundKategorieElement();
        geccoSerologischerBefundKategorieElement.setValue(KategorieDefiningCode.LABORATORY);
        geccoSerologischerBefundComposition.setKategorie(List.of(geccoSerologischerBefundKategorieElement));
    }

    private boolean isLoincCode(Coding code) {
        return AntiBodyPanelCode.LABORATORY_STUDIES.getCode().equals(code.getCode()) && AntiBodyPanelCode.LABORATORY_STUDIES.getSystem().equals(code.getSystem());
    }

    private boolean isLabratory(String code) {
        return KategorieDefiningCode.LABORATORY.getCode().equals(code);
    }

    private boolean isObservationCategory(Coding categories) {
        return categories.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category");
    }

}
