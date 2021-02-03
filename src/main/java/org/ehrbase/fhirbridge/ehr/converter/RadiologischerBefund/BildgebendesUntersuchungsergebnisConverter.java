package org.ehrbase.fhirbridge.ehr.converter.RadiologischerBefund;

import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.hl7.fhir.r4.model.DiagnosticReport;

public class BildgebendesUntersuchungsergebnisConverter {

    public void map(DiagnosticReport diagnosticReport) {

        BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation = new BildgebendesUntersuchungsergebnisObservation();
        bildgebendesUntersuchungsergebnisObservation.setLanguage(Language.DE);
        mapNameDerUntersuchung(diagnosticReport.getCode().getCoding());
        bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(diagnosticReport.getConclusionCode());
    }

    private void mapNameDerUntersuchung(){
        bildgebendesUntersuchungsergebnisObservation.setNameDerUntersuchungDefiningCode();

    }

    private void mapBefund(){

    }
}
