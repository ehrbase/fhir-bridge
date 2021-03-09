package org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
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

public class RadiologischerBefundConverter extends AbstractCompositionConverter<DiagnosticReport, GECCORadiologischerBefundComposition> {

    @Override
    public GECCORadiologischerBefundComposition convert(@NonNull DiagnosticReport diagnosticReport) {
        GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition = new GECCORadiologischerBefundComposition();
        geccoRadiologischerBefundComposition.setStartTimeValue(diagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        geccoRadiologischerBefundComposition.setEndTimeValue(diagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        mapStatus(geccoRadiologischerBefundComposition, diagnosticReport);
        mapKategorie(geccoRadiologischerBefundComposition, diagnosticReport);
        geccoRadiologischerBefundComposition.setBildgebendesUntersuchungsergebnis(new BildgebendesUntersuchungsergebnisConverter().map(diagnosticReport));
        return geccoRadiologischerBefundComposition;
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
            throw new UnprocessableEntityException("The status " + diagnosticReport.getStatus().toString() + " is not valid for radiology report.");
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
            throw new UnprocessableEntityException("The LOINC code: " + coding.getCode() + " is not valid for radiology report!");
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
