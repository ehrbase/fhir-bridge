package org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.NameDerUntersuchungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.List;

public class BildgebendesUntersuchungsergebnisObservationConverter extends DiagnosticReportToObservationConverter<BildgebendesUntersuchungsergebnisObservation> {

    @Override
    protected BildgebendesUntersuchungsergebnisObservation convertInternal(DiagnosticReport diagnosticReport) {
        BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation = new BildgebendesUntersuchungsergebnisObservation();
        mapNameDerUntersuchung(bildgebendesUntersuchungsergebnisObservation, diagnosticReport.getCode().getCoding());
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(diagnosticReport.getConclusionCode().get(0).getCoding().get(0))
                        .ifPresent(bildgebendesUntersuchungsergebnisObservation::setBefunde);
        return bildgebendesUntersuchungsergebnisObservation;
    }

    private void mapNameDerUntersuchung(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, List<Coding> coding) {
        if (coding.get(0).getCode().equals("18748-4")) {
            bildgebendesUntersuchungsergebnisObservation.setNameDerUntersuchungDefiningCode(NameDerUntersuchungDefiningCode.DIAGNOSTIC_IMAGING_STUDY);
        } else { // currently not reachable because fix pattern; therefore not tested
            throw new ConversionException("The Loinc code " + coding.get(0).getCode() + " is not supported for radiology report !");
        }
    }
}
