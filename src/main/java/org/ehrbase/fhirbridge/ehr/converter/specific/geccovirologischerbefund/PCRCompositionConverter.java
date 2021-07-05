package org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.GECCOVirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.GeccoVirologischerBefundKategorieLoincElement;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.KategorieLoincDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PCRCompositionConverter extends ObservationToCompositionConverter<GECCOVirologischerBefundComposition> {

    @Override
    public GECCOVirologischerBefundComposition convertInternal(@NonNull Observation resource) {
        GECCOVirologischerBefundComposition composition = new GECCOVirologischerBefundComposition();
        composition.setBefund(new PCRObservationConverter().convert(resource));
        composition.setStatusDefiningCode(createStatusDefiningCode(resource.getStatus()));
        composition.setKategorieDefiningCode(KategorieDefiningCode.LABORATORY);
        composition.setKategorieLoinc(createKategorieLoinc());
        return composition;
    }

    private StatusDefiningCode createStatusDefiningCode(Observation.ObservationStatus fhirStatus) {
        switch(fhirStatus.toCode()) {
            case "registered":
                return StatusDefiningCode.REGISTRIERT;
            case "preliminary":
                return StatusDefiningCode.VORLAEUFIG;
            case "final":
                return StatusDefiningCode.FINAL;
            case "amended":
                return StatusDefiningCode.GEAENDERT;
            default:
                throw new UnprocessableEntityException("createStatusDefiningCode failed. Code not found for: " + fhirStatus.toString());
        }
    }

    private List<GeccoVirologischerBefundKategorieLoincElement> createKategorieLoinc() {
        List<GeccoVirologischerBefundKategorieLoincElement> list = new ArrayList<>();
        GeccoVirologischerBefundKategorieLoincElement elem = new GeccoVirologischerBefundKategorieLoincElement();
        elem.setValue(KategorieLoincDefiningCode.LABORATORY_STUDIES_SET);
        list.add(elem);
        return list;
    }
}
