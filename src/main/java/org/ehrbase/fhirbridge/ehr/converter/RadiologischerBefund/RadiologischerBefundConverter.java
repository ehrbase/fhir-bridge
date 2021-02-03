package org.ehrbase.fhirbridge.ehr.converter.RadiologischerBefund;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.ArrayList;
import java.util.List;

public class RadiologischerBefundConverter implements CompositionConverter<GECCORadiologischerBefundComposition, DiagnosticReport> {

    @Override
    public DiagnosticReport fromComposition(GECCORadiologischerBefundComposition composition) throws CompositionConversionException {
        return new DiagnosticReport();
    }

    @Override
    public GECCORadiologischerBefundComposition toComposition(DiagnosticReport diagnosticReport) throws CompositionConversionException {
        GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition = new GECCORadiologischerBefundComposition();
        geccoRadiologischerBefundComposition.setLanguage(Language.DE);
        mapStatus(geccoRadiologischerBefundComposition, diagnosticReport);
        mapKategorie(geccoRadiologischerBefundComposition, diagnosticReport);
        geccoRadiologischerBefundComposition.setBildgebendesUntersuchungsergebnis(new BildgebendesUntersuchungsergebnisConverter().map(diagnosticReport));
        return geccoRadiologischerBefundComposition;
    }

    private void mapStatus(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, DiagnosticReport diagnosticReport) {
        if (diagnosticReport.getStatus().getDefinition().equals(StatusDefiningCode.FINAL.getCode())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (diagnosticReport.getStatus().getDefinition().equals(StatusDefiningCode.GEAENDERT.getCode())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (diagnosticReport.getStatus().getDefinition().equals(StatusDefiningCode.REGISTRIERT.getCode())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (diagnosticReport.getStatus().getDefinition().equals(StatusDefiningCode.VORLAEUFIG.getCode())) {
            geccoRadiologischerBefundComposition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        }else{
            throw new UnprocessableEntityException("adas");
        }
    }

    private void mapKategorie(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, DiagnosticReport diagnosticReport){
        for (CodeableConcept codeableConcept:diagnosticReport.getCategory()
             ) {
            for (Coding coding:codeableConcept.getCoding()
                 ) {
                mapCode(geccoRadiologischerBefundComposition, coding);
            }
        }
    }

    private void mapCode(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition, Coding coding) {
           List<RadiologischerBefundKategorieElement> radiologischerBefundKategorieElementList = getRadiologischerBefundKategorieElementsList(geccoRadiologischerBefundComposition);
            RadiologischerBefundKategorieElement radiologischerBefundKategorieElement = new RadiologischerBefundKategorieElement();
            if(coding.getCode().equals(KategorieDefiningCode.RADIOLOGY.getCode())){
                radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY);
            }else if(coding.getCode().equals(KategorieDefiningCode.RADIOLOGY_STUDIES_SET.getCode())){
                radiologischerBefundKategorieElement.setValue(KategorieDefiningCode.RADIOLOGY_STUDIES_SET);
            }else{
                throw new UnprocessableEntityException("adas");
            }
            geccoRadiologischerBefundComposition.setKategorie(radiologischerBefundKategorieElementList);
    }

    private List getRadiologischerBefundKategorieElementsList(GECCORadiologischerBefundComposition geccoRadiologischerBefundComposition){
        if(geccoRadiologischerBefundComposition.getKategorie() == null){
            return new ArrayList();
        }
        return geccoRadiologischerBefundComposition.getKategorie();
    }

}
