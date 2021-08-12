package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.GECCOEntlassungsdatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsdatenKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PatientDischargeCompositionConverter extends ObservationToCompositionConverter<GECCOEntlassungsdatenComposition> {

    @Override
    public GECCOEntlassungsdatenComposition convertInternal(@NonNull Observation resource) {
        GECCOEntlassungsdatenComposition composition = new GECCOEntlassungsdatenComposition();
        mapKategorie(composition, resource);
        composition.setEntlassungsart(new PatientDischargeAdminEntryConverter().convert(resource));

        return composition;
    }

    private void mapKategorie(GECCOEntlassungsdatenComposition composition, Observation resource) {
        List<EntlassungsdatenKategorieElement> list = new ArrayList<>();
        for(CodeableConcept category : resource.getCategory()){
            for(Coding coding : category.getCoding()){
                EntlassungsdatenKategorieElement entlassungsdatenKategorieElement = new EntlassungsdatenKategorieElement();
                entlassungsdatenKategorieElement.setValue(coding.getCode());
                list.add(entlassungsdatenKategorieElement);
            }
        }
        composition.setKategorie(list);
    }

}
