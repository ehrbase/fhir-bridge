package org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.NameDerUntersuchungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.List;

public class BildgebendesUntersuchungsergebnisObservationConverter extends DiagnosticReportToObservationConverter<BildgebendesUntersuchungsergebnisObservation> {

    @Override
    protected BildgebendesUntersuchungsergebnisObservation convertInternal(DiagnosticReport resource) {
        BildgebendesUntersuchungsergebnisObservation observation = new BildgebendesUntersuchungsergebnisObservation();
        mapNameDerUntersuchung(observation, resource.getCode().getCoding());
        observation.setBefunde(CodingToDvCodedTextConverter.getInstance().convert(resource.getConclusionCode().get(0).getCoding().get(0)));
        return observation;
    }

    private void mapNameDerUntersuchung(BildgebendesUntersuchungsergebnisObservation bildgebendesUntersuchungsergebnisObservation, List<Coding> coding) {
        if (coding.get(0).getCode().equals("18748-4")) {
            bildgebendesUntersuchungsergebnisObservation.setNameDerUntersuchungDefiningCode(NameDerUntersuchungDefiningCode.DIAGNOSTIC_IMAGING_STUDY);
        } else {
            throw new ConversionException("The Loinc code " + coding.get(0).getCode() + " is not supported for radiology report !");
        }
    }

}
