package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import liquibase.pro.packaged.G;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AdministrativesGeschlechtDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.hl7.fhir.r4.model.Patient;

import java.util.Optional;

public class GeschlechtConverter {
    public Optional<GeschlechtEvaluation> convert(Patient resource) {
        if (resource.hasGender()) {
            if (AdministrativesGeschlechtDefiningCode.getCodesAsMap().containsKey(resource.getGenderElement().getCode())){
                GeschlechtEvaluation geschlechtEvaluation = new GeschlechtEvaluation();
                geschlechtEvaluation.setAdministrativesGeschlechtDefiningCode(AdministrativesGeschlechtDefiningCode.getCodesAsMap().get(resource.getGenderElement().getCode()));
                return Optional.of(geschlechtEvaluation);
            }else{
                throw new ConversionException("The code is not supported by the bridge for gender");
            }
        } else {
            return Optional.empty();
        }
    }

}
