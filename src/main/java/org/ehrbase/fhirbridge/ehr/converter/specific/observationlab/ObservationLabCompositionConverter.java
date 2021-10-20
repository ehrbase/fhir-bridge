package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborbefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.List;

public class ObservationLabCompositionConverter extends ObservationToCompositionConverter<GECCOLaborbefundComposition> {

    @Override
    public GECCOLaborbefundComposition convertInternal(@NonNull Observation resource) {
        GECCOLaborbefundComposition composition = new GECCOLaborbefundComposition();
        composition.setLaborergebnis(List.of(new LaborergebnisObservationConverter().convert(resource)));
        composition.setStatusDefiningCode(getRegisterEintrag(resource));
        setKategorieValue(resource, composition);
        return composition;
    }

    private void setKategorieValue(Observation resource, GECCOLaborbefundComposition composition) {
        for(CodeableConcept codeableConcept : resource.getCategory()){
            convertKategorieValue(codeableConcept, composition);
        }
    }

    private void convertKategorieValue(CodeableConcept codeableConcept, GECCOLaborbefundComposition composition) {
        for(Coding coding : codeableConcept.getCoding()){
            if (coding.getSystem().equals(CodeSystem.HL7_OBSERVATI0N_CATEGORY.getUrl())) {
                LaborbefundKategorieElement labortestKategorieElement = new LaborbefundKategorieElement();
                labortestKategorieElement.setValue(coding.getCode());
                composition.setKategorie(List.of(labortestKategorieElement));
            }
        }
    }

    private StatusDefiningCode getRegisterEintrag(Observation resource) {
        switch (resource.getStatus()) {
            case FINAL:
                return StatusDefiningCode.FINAL;
            case CORRECTED:
                return StatusDefiningCode.GEAENDERT;
            case PRELIMINARY:
                return StatusDefiningCode.VORLAEUFIG;
            case REGISTERED:
                return StatusDefiningCode.REGISTRIERT;
            default:
                throw new InvalidStatusCodeException(resource.getStatus().toString());
        }
    }
}
