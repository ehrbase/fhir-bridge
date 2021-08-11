package org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureCompositionConverter extends ObservationToCompositionConverter<BlutdruckComposition> {

    @Override
    protected BlutdruckComposition convertInternal(@NonNull Observation resource) {
        BlutdruckComposition result = new BlutdruckComposition();
        mapStatus(result, resource);
        mapKategorie(result, resource);
        result.setBlutdruck(new BlutdruckObservationConverter().convert(resource));
        return result;
    }

    private void mapKategorie(BlutdruckComposition composition, Observation resource) {
        List<BlutdruckKategorieElement> list = new ArrayList<>();
        for(CodeableConcept category : resource.getCategory()){
            for(Coding coding : category.getCoding()){
                BlutdruckKategorieElement blutdruckKategorieElement = new BlutdruckKategorieElement();
                blutdruckKategorieElement.setValue(coding.getCode());
                list.add(blutdruckKategorieElement);
            }
        }
        composition.setKategorie(list);
    }

    private void mapStatus(BlutdruckComposition composition, Observation obs) {
        String status = obs.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + obs.getStatus().toString() + " is not valid for known exposure.");
        }
    }
}
