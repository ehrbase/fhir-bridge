package org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerBefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;
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
        mapStatus(composition, resource);
        mapKategorie(composition, resource);
        composition.setBildgebendesUntersuchungsergebnis(List.of(new BildgebendesUntersuchungsergebnisObservationConverter().convert(resource)));
        return composition;
    }

    private void mapStatus(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, DiagnosticReport diagnosticReport) {
        String status = diagnosticReport.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + diagnosticReport.getStatus().toString() + " is not valid for radiology report.");
        }
    }

    private void mapKategorie(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, DiagnosticReport diagnosticReport) {
        for (CodeableConcept codeableConcept : diagnosticReport.getCategory()
        ) {
            for (Coding coding : codeableConcept.getCoding()
            ) {
                mapCode(geccoRadiologischerBefundComposition, coding);
            }
        }
    }

    private void mapCode(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, Coding coding) {
        List<RadiologischerBefundKategorieElement> radiologischerBefundKategorieElementList = getRadiologischerBefundKategorieElementsList(geccoRadiologischerBefundComposition);
        RadiologischerBefundKategorieElement radiologischerBefundKategorieElement = new RadiologischerBefundKategorieElement();
        if (coding.getCode().equals(KategorieDefiningCode.RADIOLOGY.getCode())) {
            radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY);
        } else if (coding.getCode().equals(KategorieDefiningCode.RADIOLOGY_STUDIES_SET.getCode())) {
            radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY_STUDIES_SET);
        } else {
            throw new ConversionException("The LOINC code: " + coding.getCode() + " is not valid for radiology report!");
        }
        radiologischerBefundKategorieElementList.add(radiologischerBefundKategorieElement);
        geccoRadiologischerBefundComposition.setKategorie(radiologischerBefundKategorieElementList);
    }

    private List getRadiologischerBefundKategorieElementsList(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition) {
        if (geccoRadiologischerBefundComposition.getKategorie() == null) {
            return new ArrayList();
        }
        return geccoRadiologischerBefundComposition.getKategorie();
    }

}
