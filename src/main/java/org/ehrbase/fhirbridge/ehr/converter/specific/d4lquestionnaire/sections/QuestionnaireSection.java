package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public abstract class QuestionnaireSection {

    protected final Language language;
    protected final TemporalAccessor authored;

    public QuestionnaireSection(Language language, TemporalAccessor authored) {
        this.language = language;
        this.authored = authored;
    }

    public abstract void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item);

    protected Optional<Object> getValueCode(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        Optional<Object> code = Optional.empty();
        try {
            code = Optional.of(value.getAnswer().get(0).getValueCoding().getCode());
            return code;
        } catch (NullPointerException | FHIRException e) {
            return Optional.empty();
        }
    }

    protected Optional<TemporalAccessor> getValueAsDate(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        return Optional.of(LocalDate.parse(value.getAnswer().get(0).getValueDateType().getValueAsString()));
    }

    protected String getQuestionValueCodeToString(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        return getValueCode(question).get().toString();
    }

    protected Boolean getQuestionLoincYesNoToBoolean(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        return yesNoLoincCodeToBoolean(getValueCode(question).get().toString());
    }

    private Boolean yesNoLoincCodeToBoolean(String code) {
        if (code.equals("LA33-6")) {
            return true;
        } else if (code.equals("LA32-8")) {
            return false;
        } else {
            throw new ConversionException("\"" + code + "\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
        }
    }

}
