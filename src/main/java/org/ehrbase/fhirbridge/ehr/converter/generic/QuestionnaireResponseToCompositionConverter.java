package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<QuestionnaireResponse, C> {

    @Override
    public C convert(@NonNull QuestionnaireResponse resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(getStartTime(resource));
        return composition;
    }

    public TemporalAccessor getStartTime(QuestionnaireResponse resource){
        return TimeConverter.convertQuestionnaireResponseTime(resource);
    }
}
