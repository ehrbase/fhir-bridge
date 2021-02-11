package org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BefundeDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.NameDerUntersuchungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.List;

public class BildgebendesUntersuchungsergebnisConverter {

    public List<BildgebendesUntersuchungsergebnisObservation> map(DiagnosticReport diagnosticReport) {
        BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation = new BildgebendesUntersuchungsergebnisObservation();

        bildgebendesUntersuchungsergebnisObservation.setLanguage(Language.DE);
        bildgebendesUntersuchungsergebnisObservation.setTimeValue(diagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        bildgebendesUntersuchungsergebnisObservation.setOriginValue(diagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        bildgebendesUntersuchungsergebnisObservation.setSubject(new PartySelf());

        mapNameDerUntersuchung(bildgebendesUntersuchungsergebnisObservation, diagnosticReport.getCode().getCoding());
        mapBefund(bildgebendesUntersuchungsergebnisObservation, diagnosticReport.getConclusion());
        return List.of(bildgebendesUntersuchungsergebnisObservation);
    }

    private void mapNameDerUntersuchung(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, List<Coding> coding){
        if(coding.get(0).getCode().equals("18748-4")){
            bildgebendesUntersuchungsergebnisObservation.setNameDerUntersuchungDefiningCode(NameDerUntersuchungDefiningCode.DIAGNOSTIC_IMAGING_STUDY);
        }else{
            throw new UnprocessableEntityException("The Loinc code "+coding.get(0).getCode()+" is not supported for radiology report !");
        }
    }

    private void mapBefund(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, String conclusion){
       if(conclusion.equals(BefundeDefiningCode.COVID19_TYPISCHER_BEFUND.getValue())){
            bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.COVID19_TYPISCHER_BEFUND);
       }else if(conclusion.equals(BefundeDefiningCode.NORMALBEFUND.getValue())){
           bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.NORMALBEFUND);
       }else if(conclusion.equals(BefundeDefiningCode.UNSPEZIFISCHER_BEFUND.getValue())){
           bildgebendesUntersuchungsergebnisObservation.setBefundeDefiningCode(BefundeDefiningCode.UNSPEZIFISCHER_BEFUND);
       }else{
           throw new UnprocessableEntityException("The SNOMED code: "+conclusion+", is not supported for radiology report !");
       }
    }
}
