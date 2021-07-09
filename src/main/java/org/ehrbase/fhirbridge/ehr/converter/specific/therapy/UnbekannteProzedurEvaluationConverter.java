package org.ehrbase.fhirbridge.ehr.converter.specific.therapy;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NameDerProzedurDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.UnbekannteProzedurEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

public class UnbekannteProzedurEvaluationConverter extends EntryEntityConverter<Procedure, UnbekannteProzedurEvaluation> {
    @Override
    protected UnbekannteProzedurEvaluation convertInternal(Procedure resource) {
        UnbekannteProzedurEvaluation unbekannteProzedur = new UnbekannteProzedurEvaluation();
        unbekannteProzedur.setAussageUeberDieFehlendeInformationValue(resource.getStatus().getDisplay());
        try {
            unbekannteProzedur.setUnbekannteProzedurDefiningCode(mapNameDerProzedur(resource));
        } catch (ConversionException e) {
            throw new ConversionException("Some parts of the unknown procedure did not contain the required elements. "
                    + e.getMessage(), e);
        }
        return unbekannteProzedur;
    }

    private NameDerProzedurDefiningCode mapNameDerProzedur(Procedure procedure) throws ConversionException {
        Coding coding = procedure.getCode().getCoding().get(0);
        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && NameDerProzedurDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
            return NameDerProzedurDefiningCode.getCodesAsMap().get(coding.getCode());
        } else {
            throw new ConversionException("Invalid name of procedure");
        }
    }
}
