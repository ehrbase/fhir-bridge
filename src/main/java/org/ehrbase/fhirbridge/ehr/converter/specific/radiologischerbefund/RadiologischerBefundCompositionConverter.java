package org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RadiologischerBefundCompositionConverter extends DiagnosticReportToCompositionConverter<GECCORadiologischerBefundComposition> {

    @Override
    public GECCORadiologischerBefundComposition convertInternal(@NonNull DiagnosticReport resource) {
        GECCORadiologischerBefundComposition composition = new GECCORadiologischerBefundComposition();
        mapKategorie(composition, resource);
        composition.setBildgebendesUntersuchungsergebnis(List.of(new BildgebendesUntersuchungsergebnisObservationConverter().convert(resource)));
        return composition;
    }

    private void mapKategorie(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, DiagnosticReport diagnosticReport) {
        List<RadiologischerBefundKategorieElement> radiologischerBefundKategorieElementList = getRadiologischerBefundKategorieElementsList(geccoRadiologischerBefundComposition);
        for (CodeableConcept codeableConcept : diagnosticReport.getCategory()
        ) {
            for (Coding coding : codeableConcept.getCoding()
            ) {
                radiologischerBefundKategorieElementList.add(mapKategorieCode(coding));
            }
            geccoRadiologischerBefundComposition.setKategorie(radiologischerBefundKategorieElementList);

        }
    }

    private RadiologischerBefundKategorieElement mapKategorieCode(Coding coding) {
        RadiologischerBefundKategorieElement radiologischerBefundKategorieElement = new RadiologischerBefundKategorieElement();
        if (coding.getCode().equals(KategorieDefiningCode.RADIOLOGY.getCode())) {
            radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY);
        } else if (coding.getCode().equals(KategorieDefiningCode.RADIOLOGY_STUDIES_SET.getCode())) {
            radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY_STUDIES_SET);
        } else { // currently not reachable because fix pattern; therefore not tested
            throw new ConversionException("The LOINC code: " + coding.getCode() + " is not valid for radiology report!");
        }
        return radiologischerBefundKategorieElement;
    }

    private List<RadiologischerBefundKategorieElement> getRadiologischerBefundKategorieElementsList(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition) {
        if (geccoRadiologischerBefundComposition.getKategorie() == null) {
            return new ArrayList<>();
        }
        return geccoRadiologischerBefundComposition.getKategorie();
    }

}
