package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class EncounterToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Encounter, C> {

    @Override
    public C convert(@NonNull Encounter resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertEncounterTime(resource)); // StartTimeValue
        TimeConverter.convertEncounterEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Encounter encounter) {
        return new PartySelf();
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Encounter encounter) {
        if (!encounter.hasServiceProvider()) {
            return Optional.empty();
        }
        return Optional.of(new ReferenceToPartyIdentifiedConverter().convert(encounter.getServiceProvider()));
    }
}
