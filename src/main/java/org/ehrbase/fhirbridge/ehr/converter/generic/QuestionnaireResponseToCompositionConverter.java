package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public abstract class QuestionnaireResponseToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<QuestionnaireResponse, C> {

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull QuestionnaireResponse resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(getStartTime(resource));
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(QuestionnaireResponse questionnaire) {
        if (!questionnaire.hasAuthor()) {
            return new PartySelf();
        }
        return new ReferenceToPartyIdentifiedConverter().convert(questionnaire.getAuthor());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(QuestionnaireResponse questionnaire) {
        if (!questionnaire.hasAuthor() ||
                !Resources.isReferenceType(questionnaire.getAuthor(), ResourceType.Organization)) {
            return Optional.empty();
        }
        return Optional.of(converter.convert(questionnaire.getAuthor()));
    }

    public TemporalAccessor getStartTime(QuestionnaireResponse resource) {
        return TimeConverter.convertQuestionnaireResponseTime(resource);
    }
}
