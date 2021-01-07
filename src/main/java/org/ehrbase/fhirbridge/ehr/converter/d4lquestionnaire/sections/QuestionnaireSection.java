package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public abstract class QuestionnaireSection {

    protected final TemporalAccessor authored;

    public QuestionnaireSection(TemporalAccessor authored) {
        this.authored = authored;
    }

    Optional<Object> getValueCode(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        Optional<Object> code = Optional.empty();
        try {
            code = Optional.of(value.getAnswer().get(0).getValueCoding().getCode());
            return code;
        } catch (NullPointerException | FHIRException e) { //TODO fix to support every value (at the moment only codes or empty)
            return Optional.empty();
        }
    }

    Optional<TemporalAccessor>  getValueAsDate(QuestionnaireResponse.QuestionnaireResponseItemComponent value){
        return Optional.of(LocalDate.parse(value.getAnswer().get(0).getValueDateType().getValueAsString()));
    }

    String getQuestionValueCodeToString(QuestionnaireResponse.QuestionnaireResponseItemComponent question){
        return getValueCode(question).get().toString();
    }

    Boolean getQuestionLoincYesNoToBoolean(QuestionnaireResponse.QuestionnaireResponseItemComponent question){
        return yesNoLoincCodeToBoolean(getValueCode(question).get().toString());
    }

    Boolean getQuestionLoincYesNoDontKnowToBoolean(QuestionnaireResponse.QuestionnaireResponseItemComponent question){
        return yesNotDontKnowLoinToBoolean(getValueCode(question).get().toString());
    }


    private Boolean yesNoLoincCodeToBoolean(String code){
        if (code.equals("LA33-6")){
            return true;
        }else if(code.equals("LA32-8") || code.equals("LA46-8")){ //TODO how to handle Others LOINC code in openEHR
            return false;
        }else{
            throw new UnprocessableEntityException( "\""+ code + "\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
        }
    }

    private Boolean yesNotDontKnowLoinToBoolean(String code) {
        if (code.equals("LA33-6")) {
            return true;
        } else if (code.equals("LA32-8") || code.equals("LA12688-0")) {
            return false;
        } else {
            throw new UnprocessableEntityException("\"" + code + "\" cannot be mapped to boolean, has to be either LA33-6, LA33-8 or LA12688-0");
        }
    }

/*    LocalDate getValueDate(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        return LocalDate.parse(value.getAnswer().get(0).getValueDateType().getValueAsString());
    }*/

    public abstract void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item);

    public abstract Object toComposition();

}
