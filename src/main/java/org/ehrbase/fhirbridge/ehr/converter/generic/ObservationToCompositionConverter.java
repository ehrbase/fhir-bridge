package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

/**
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class ObservationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Observation, C> {

    @Override
    public C convert(@NonNull Observation resource) {
        C composition = super.convert(resource);

        // Mandatory
        composition.setStartTimeValue(TimeConverter.convertObservationTime(resource)); // StartTimeValue
        composition.setComposer(getComposerOrDefault(resource)); // Composer

        // Optional
        TimeConverter.convertObservationEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue

        return composition;
    }

    protected PartyProxy getComposerOrDefault(Observation resource) {
        return resource.getPerformer()
                .stream()
                .map(reference -> {
                    PartyIdentified composer = new PartyIdentified();
                    DvIdentifier identifier = new DvIdentifier();
                    if (reference.hasReference()) {
                        identifier.setId(reference.getReference());
                    } else if (reference.hasIdentifier()) {
                        identifier.setAssigner(reference.getIdentifier().getSystem());
                        identifier.setId(reference.getIdentifier().getValue());
                    }
                    composer.addIdentifier(identifier);
                    return (PartyProxy) composer;
                })
                .findFirst()
                .orElse(new PartySelf());
    }
}
