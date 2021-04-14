package org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerBefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BefundeDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.NameDerUntersuchungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.List;

public class BildgebendesUntersuchungsergebnisObservationConverter extends DiagnosticReportToObservationConverter<BildgebendesUntersuchungsergebnisObservation> {

    @Override
    protected BildgebendesUntersuchungsergebnisObservation convertInternal(DiagnosticReport resource) {
        BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation = new BildgebendesUntersuchungsergebnisObservation();
        mapNameDerUntersuchung(bildgebendesUntersuchungsergebnisObservation, resource.getCode().getCoding());
        mapBefund(bildgebendesUntersuchungsergebnisObservation, resource.getConclusionCode().get(0).getCoding().get(0).getCode());
        return bildgebendesUntersuchungsergebnisObservation;
    }

    private void mapNameDerUntersuchung(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, List<Coding> coding) {
        if (coding.get(0).getCode().equals("18748-4")) {
            bildgebendesUntersuchungsergebnisObservation.setNameDerUntersuchungDefiningCode(NameDerUntersuchungDefiningCode.DIAGNOSTIC_IMAGING_STUDY);
        } else {
            throw new ConversionException("The Loinc code " + coding.get(0).getCode() + " is not supported for radiology report !");
        }
    }

    private void mapBefund(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, String conclusion) {
        if (conclusion.contains(BefundeDefiningCode.COVID19_TYPISCHER_BEFUND.getCode())) {
            bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.COVID19_TYPISCHER_BEFUND);
        } else if (conclusion.contains(BefundeDefiningCode.NORMALBEFUND.getCode())) {
            bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.NORMALBEFUND);
        } else if (conclusion.contains(BefundeDefiningCode.UNSPEZIFISCHER_BEFUND.getCode())) {
            bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.UNSPEZIFISCHER_BEFUND);
        } else {
            throw new ConversionException("The SNOMED code: " + conclusion + ", is not supported for radiology report !");
        }
    }



}
