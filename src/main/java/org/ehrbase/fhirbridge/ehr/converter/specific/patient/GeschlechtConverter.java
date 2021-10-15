package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.hl7.fhir.r4.model.Patient;

import java.util.Optional;

public class GeschlechtConverter {

    public Optional<GeschlechtEvaluation> convert(Patient resource) {
        if (resource.hasGender()) {
            GeschlechtEvaluation geschlechtEvaluation = new GeschlechtEvaluation();
            parseCode(resource.getGenderElement().getCode()).ifPresent(geschlechtEvaluation::setAdministrativesGeschlecht);
            return Optional.of(geschlechtEvaluation);
        }
        return Optional.empty();
    }

    private Optional<DvCodedText> parseCode(String code) {
        return Optional.of(new DvCodedText("", new CodePhrase(new TerminologyId("http://hl7.org/fhir/administrative-gender", ""), code)));
    }
}

