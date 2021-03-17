package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

public abstract class QuestionnaireResponseToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<QuestionnaireResponse, C> {

    @Override
    public C convert(@NonNull QuestionnaireResponse resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertQuestionnaireResponseTime(resource));
        return composition;
    }

}
