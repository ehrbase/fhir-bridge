package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import io.micrometer.core.lang.NonNull;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;

import java.util.Optional;

public abstract class CompositionToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Composition, C> {

    @Override
    public C convert(@NonNull Composition resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertCompositionTime(resource));
        //TimeConverter.convertConditionEndTime(resource).ifPresent(composition::setEndTimeValue);
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Composition resource) {
        if (!resource.hasAuthor()) {
            return new PartySelf();
        }
        return new ReferenceToPartyIdentifiedConverter().convert(resource.getAuthor().get(0));
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Composition resource) {
        return Optional.empty();
    }
}
