package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import io.micrometer.core.lang.NonNull;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Condition;

import java.util.Optional;

public abstract class ConditionToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Condition, C> {

    @Override
    public C convert(@NonNull Condition resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertConditionTime(resource));
        TimeConverter.convertConditionEndTime(resource).ifPresent(composition::setEndTimeValue);
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Condition resource) {
        if (!resource.hasRecorder()) {
            return new PartySelf();
        }
        return new ReferenceToPartyIdentifiedConverter().convert(resource.getRecorder());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Condition resource) {
        return Optional.empty();
    }
}
