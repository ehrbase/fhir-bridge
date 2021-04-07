package org.ehrbase.fhirbridge.ehr.converter.generic;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ElementConverter;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public abstract class QuestionnaireResponseItemToEntryEntityConverter<E extends EntryEntity> implements ElementConverter<QuestionnaireResponse.QuestionnaireResponseItemComponent, E> {

    @Override
    public E convert(@NonNull QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent, Language language, TemporalAccessor temporalAccessor) {
        E entity = convertInternal(questionnaireResponseItemComponent);
        entity.setLanguage(language);
        entity.setSubject(new PartySelf());
        return entity;
    }

    protected abstract E convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent);

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
            throw new UnprocessableEntityException("\"" + code + "\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
        }
    }

}
